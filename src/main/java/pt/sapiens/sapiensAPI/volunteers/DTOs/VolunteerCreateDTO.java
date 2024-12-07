package pt.sapiens.sapiensAPI.volunteers.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class VolunteerCreateDTO {

    @NotNull
    @Size(min = 3, max = 12)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 12)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 12)
    private String password;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthday;

    @NotNull
    @Pattern(regexp = "^(9)[0-9]{8}$")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^[0-9]{9}$")
    private String civilId;
}