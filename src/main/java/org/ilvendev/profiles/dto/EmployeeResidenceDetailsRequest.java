package org.ilvendev.profiles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeResidenceDetailsRequest {
    @Schema(description = "Street address", example = "123 Main St")
    @NotBlank(message = "Address is required")
    private String address;

    @Schema(description = "City", example = "New York")
    @NotBlank(message = "City is required")
    private String city;

    @Schema(description = "State", example = "NY")
    @NotBlank(message = "State is required")
    private String state;

    @Schema(description = "ZIP code", example = "10001")
    @NotBlank(message = "ZIP code is required")
    private String zip;

    @Schema(description = "Country", example = "USA")
    @NotBlank(message = "Country is required")
    private String country;
}
