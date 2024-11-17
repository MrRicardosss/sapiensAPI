# ec2 instance

terraform {

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }

    tls = {
      source  = "hashicorp/tls"
      version = "~> 4.0.5"
    }

    cloudinit = {
      source  = "hashicorp/cloudinit"
      version = "~> 2.3.4"
    }
  }

  required_version = "~> 1.3"
}

provider "aws" {
  region = "us-east-2"
}

# ---------- INPUT --------------------- 

locals {
  prefix         = "ric"
  public_ssh_key = file("${path.module}/id_rsa.pub")

  ip_address_1 = "92.70.51.57"
  ip_address_2 = "94.60.165.4"

  root_volume_size_gb = 30
  instance_type       = "m8g.large"

  cannonical_aws_account_id = "099720109477"
}


# ---------- DATA -------------------
data "aws_ami" "ubuntu_ami" {
  most_recent = true
  owners      = [local.cannonical_aws_account_id]

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name   = "root-device-type"
    values = ["ebs"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}


# ------------- RESOURCES -----------------------


# Filter out local zones, which are not currently supported 
# with managed node groups
data "aws_availability_zones" "available" {
  filter {
    name   = "opt-in-status"
    values = ["opt-in-not-required"]
  }
}

module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "5.13.0"

  name = "ric-vpc"

  cidr = "10.0.0.0/16"
  azs  = slice(data.aws_availability_zones.available.names, 0, 3)

  private_subnets  = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
  public_subnets   = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]
  database_subnets = ["10.0.7.0/24", "10.0.8.0/24", "10.0.9.0/24"]

  database_subnet_group_name = "rds_subnet_group"

  enable_nat_gateway   = true
  single_nat_gateway   = true
  enable_dns_hostnames = true

  create_database_subnet_group       = true
  create_database_subnet_route_table = true

  enable_flow_log       = true
  flow_log_traffic_type = "REJECT"

  create_flow_log_cloudwatch_log_group = true
  create_flow_log_cloudwatch_iam_role  = true
  flow_log_max_aggregation_interval    = 60

  tags = {
    Name = "ric"
  }

}


resource "aws_ebs_encryption_by_default" "enabled" {
  enabled = true
}

resource "aws_key_pair" "default" {
  key_name   = "${local.prefix}-ssh-key"
  public_key = local.public_ssh_key
}


module "https_443_security_group" {
  source              = "terraform-aws-modules/security-group/aws//modules/https-443"
  name                = "https_security_group"
  version             = "~> 5.0"
  vpc_id              = module.vpc.vpc_id
  ingress_cidr_blocks = [module.vpc.vpc_cidr_block]
}

module "ssh_security_group" {
  name    = "ssh_security_group"
  source  = "terraform-aws-modules/security-group/aws//modules/ssh"
  version = "~> 5.0"
  vpc_id  = module.vpc.vpc_id
  ingress_cidr_blocks = [
    local.ip_address_1,
    local.ip_address_2,
  ]
}

module "http_80_security_group" {
  source  = "terraform-aws-modules/security-group/aws//modules/http-80"
  version = "~> 5.0"

  name = "http_80_security_group"

  vpc_id = module.vpc.vpc_id
}

module "ec2_instance" {
  create = true

  ami    = data.aws_ami.ubuntu_ami.id
  source = "terraform-aws-modules/ec2-instance/aws"

  name = "${local.prefix}-machine"

  instance_type = local.instance_type

  key_name   = resource.aws_key_pair.default.key_name
  subnet_id  = module.vpc.public_subnets[0]
  monitoring = true

  vpc_security_group_ids = [
    module.ssh_security_group.security_group_id,
    module.https_443_security_group.security_group_id,
    module.http_80_security_group.security_group_id
  ]

  root_block_device = [
    {
      volume_size = local.root_volume_size_gb
    }
  ]

  instance_tags = {
    Name = "${local.prefix}-machine"
  }

  tags = {
    Terraform   = "true"
    Environment = "production"
  }

  metadata_options = {
    http_tokens = "required"
  }

}

resource "aws_eip" "ip" {
  domain = "vpc"
  tags = {
    Name = "${local.prefix}-ip"
  }
}

resource "aws_eip_association" "eip_assoc" {
  instance_id   = module.ec2_instance.id
  allocation_id = resource.aws_eip.ip.id
  depends_on = [
    module.vpc,
  ]
}

# ----------------- OUTPUT --------------------

output "ssh_command" {
  value = try("ssh -i id_rsa ubuntu@${aws_eip.ip.public_ip}", null)
}
