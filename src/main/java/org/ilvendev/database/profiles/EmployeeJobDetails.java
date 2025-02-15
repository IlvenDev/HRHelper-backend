package org.ilvendev.database.profiles;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.enums.Department;
import org.ilvendev.enums.EmploymentType;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee_job_details")
public class EmployeeJobDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jobTitle;
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Temporal(TemporalType.DATE)
    @Nullable
    private Date terminationDate;

    @ManyToOne
    @JoinColumn(name = "directSupervisorId")
    private Employee directSupervisor;

    private String workLocation;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
