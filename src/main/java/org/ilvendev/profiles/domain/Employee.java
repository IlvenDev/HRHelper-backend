package org.ilvendev.profiles.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.payroll.domain.EmployeePaymentDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String pesel;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String sex;

    @OneToOne(mappedBy = "employee", cascade = {CascadeType.ALL, CascadeType.MERGE})
    private EmergencyContact emergencyContact;

    @OneToOne(mappedBy = "employee", cascade = {CascadeType.ALL, CascadeType.MERGE})
    private EmployeeJobDetails jobDetails;

    @OneToOne(mappedBy = "employee", cascade = {CascadeType.ALL, CascadeType.MERGE})
    private EmployeeResidenceDetails residenceDetails;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<AttendanceTime> attendanceTimes = new HashSet<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Leave> leaves = new HashSet<>();


}