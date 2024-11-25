package pt.sapiens.sapiensAPI.organizations;

import jakarta.persistence.*;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String profilePicture;

    @Column
    private String website;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;
}

/*
id int [PK]
name varchar [not null]
email varchar [not null, unique]
password varchar [not null]
profilePicture varchar [not null]
websiteUrl varchar
phoneNumber varchar [not null]
address varchar [not null]
*/
