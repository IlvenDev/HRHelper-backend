package org.ilvendev.profiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_job_details")
public class EmployeeJobDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobDetailsId;
    private String jobTitle;
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Temporal(TemporalType.DATE)
    private Date terminationDate;

    @ManyToOne
    @JoinColumn(name = "directSupervisorId")
    private Employee directSupervisor;

    private String workLocation;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
