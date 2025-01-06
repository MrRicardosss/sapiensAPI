package pt.sapiens.sapiensAPI.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class OrganizationCreateDTO {
    @NotNull
    @Size(max = 50)
    private String name;

    @NotBlank
    private String imageUrl;

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
