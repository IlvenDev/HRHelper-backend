package org.ilvendev.profiles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
    @Schema(description = "Employee's first name", example = "John")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String name;
    @Schema(description = "Employee's last name", example = "Doe")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastname;
    @Schema(description = "Employee's pesel", example = "78924512521")
    @NotBlank(message = "Pesel is required")
    @Size(min = 11, max = 11)
    private String pesel;
    @Schema(description = "Employee's phone number", example = "256896123")
    @NotBlank(message = "Phone number is required")
    @Size(min = 9, max = 9 )
    private String phone;
    @Schema(description = "Employee's email", example = "johndoe@mock.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Schema(description = "Employee's date of birth", example = "1997-11-05")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Schema(description = "Employee's sex", example = "M")
    private Character sex;
}
