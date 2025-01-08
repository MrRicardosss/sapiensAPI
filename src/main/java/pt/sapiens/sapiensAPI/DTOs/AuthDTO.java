package pt.sapiens.sapiensAPI.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 12)
    private String password;
}
