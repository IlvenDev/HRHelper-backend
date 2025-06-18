package org.ilvendev.profiles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @Schema(description = "Employee's first name", example = "John")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String name;

    @Schema(description = "Employee's last name", example = "Doe")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastname;

    @Schema(description = "Employee's pesel", example = "78924512521")
    @NotBlank(message = "Pesel is required")
    @Pattern(regexp = "\\d{11}", message = "Pesel must be exactly 11 digits")
    private String pesel;

    @Schema(description = "Employee's phone number", example = "256896123")
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{9}", message = "Phone number must be exactly 9 digits")
    private String phone;

    @Schema(description = "Employee's email", example = "johndoe@mock.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Employee's date of birth", example = "1997-11-05")
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Schema(description = "Employee's sex", example = "M")
    @NotNull(message = "Sex is required")
    @Pattern(regexp = "[MF]", message = "Sex must be 'M' or 'F'")
    private String sex;

    @NotNull(message = "Job details are required")
    @Valid
    private EmployeeJobDetailsRequest jobDetails;

    @NotNull(message = "Residence details are required")
    @Valid
    private EmployeeResidenceDetailsRequest residenceDetails;

    @NotNull(message = "Emergency contact is required")
    @Valid
    private EmergencyContactRequest emergencyContact;
}
