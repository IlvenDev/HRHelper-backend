package org.ilvendev.profiles.employee;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.attendance.AttendanceTimes;
import org.ilvendev.attendance.Leaves;
import org.ilvendev.payroll.EmployeePaymentDetails;
import org.ilvendev.profiles.emergency_contact.EmergencyContact;
import org.ilvendev.profiles.job_details.EmployeeJobDetails;
import org.ilvendev.profiles.residence_details.EmployeeResidenceDetails;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String pesel;
    private String phone;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(columnDefinition = "CHAR(1)")
    private char sex;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmergencyContact emergencyContact;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeeJobDetails jobDetails;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeeResidenceDetails residenceDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<AttendanceTimes> attendanceTimes;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<Leaves> leaves;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeePaymentDetails employeePaymentDetails;

}
