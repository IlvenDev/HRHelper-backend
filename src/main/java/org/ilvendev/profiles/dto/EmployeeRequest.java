package org.ilvendev.profiles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastname;
    @NotBlank(message = "Pesel is required")
    private String pesel;
    @NotBlank(message = "Phone number is required")
    private String phone;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Sex is required")
    private Character sex;
}
