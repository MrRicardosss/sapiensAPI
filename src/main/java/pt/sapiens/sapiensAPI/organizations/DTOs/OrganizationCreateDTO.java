package pt.sapiens.sapiensAPI.organizations.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class OrganizationCreateDTO {
    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 12)
    private String password;

    @URL
    private String website;

    @NotNull
    @Pattern(regexp = "^(9)[0-9]{8}$")
    private String phoneNumber;

    @NotNull
    private String address;
}
