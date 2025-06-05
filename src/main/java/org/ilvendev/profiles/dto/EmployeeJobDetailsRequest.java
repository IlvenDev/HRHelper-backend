package org.ilvendev.profiles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.ilvendev.enums.Department;
import org.ilvendev.enums.EmploymentType;

import java.time.LocalDate;

@Data
public class EmployeeJobDetailsRequest {
    @Schema(description = "Job title", example = "Software Engineer")
    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @Schema(description = "Job description", example = "Develops backend services.")
    @NotBlank(message = "Job description is required")
    private String jobDescription;

    @Schema(description = "Department", example = "IT")
    private Department department;

    @Schema(description = "Work location", example = "Headquarters")
    @NotBlank(message = "Work location is required")
    private String workLocation;

    @Schema(description = "Employment type", example = "FULL_TIME")
    private EmploymentType employmentType;

    @Schema(description = "Employment start date", example = "2022-01-01")
    private LocalDate employmentDate;

    @Schema(description = "Termination date (if applicable)", example = "2023-12-31")
    private LocalDate terminationDate;

    @Schema(description = "Supervisor employee ID", example = "3")
    private Integer directSupervisorId;
}
