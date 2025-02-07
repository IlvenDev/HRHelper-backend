package org.ilvendev.database.profiles;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.database.attendance.AttendanceTimes;
import org.ilvendev.database.attendance.Leaves;
import org.ilvendev.database.payroll.EmployeePaymentDetails;

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
