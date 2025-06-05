package org.ilvendev.profiles.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.ilvendev.enums.Department;
import org.ilvendev.enums.EmploymentType;
import org.ilvendev.profiles.domain.Employee;

import java.time.LocalDate;

@Data
public class EmployeeJobDetailsResponse {
    private int id;
    private String jobTitle;
    private String jobDescription;
    private Department department;
    private String workLocation;
    private EmploymentType employmentType;
    private LocalDate employmentDate;
    private LocalDate terminationDate;
    private Employee directSupervisor;
}
