package org.ilvendev.profiles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmergencyContactRequest {
    @Schema(description = "First name of emergency contact", example = "Jane")
    @NotBlank(message = "Contact first name is required")
    @Size(min = 2, max = 50)
    private String name;

    @Schema(description = "Last name of emergency contact", example = "Doe")
    @NotBlank(message = "Contact last name is required")
    @Size(min = 2, max = 50)
    private String lastname;

    @Schema(description = "Phone number of emergency contact", example = "555123456")
    @NotBlank(message = "Contact phone is required")
    @Size(min = 9, max = 15)
    private String phone;
}
