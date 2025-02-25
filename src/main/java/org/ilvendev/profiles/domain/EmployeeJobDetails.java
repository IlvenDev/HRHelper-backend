package org.ilvendev.profiles.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.enums.Department;
import org.ilvendev.enums.EmploymentType;

import java.time.LocalDate;

@Entity
@Table(name = "employee_job_details")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeJobDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String jobTitle;
    @Column(nullable = false)
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private String workLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentType employmentType;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate employmentDate;

    @Temporal(TemporalType.DATE)
    private LocalDate terminationDate;

    @ManyToOne
    @JoinColumn(name = "directSupervisorId")
    private Employee directSupervisor;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}


