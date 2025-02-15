package org.ilvendev.database.profiles;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.database.attendance.AttendanceTime;
import org.ilvendev.database.attendance.Leave;
import org.ilvendev.database.payroll.EmployeePaymentDetails;

import java.sql.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String pesel;
    private String phone;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(columnDefinition = "CHAR(1)")
    private Character sex;

    @Nullable
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmergencyContact emergencyContact;

    @Nullable
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeeJobDetails jobDetails;

    @Nullable
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeeResidenceDetails residenceDetails;

    @Nullable
    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<AttendanceTime> attendanceTimes;

    @Nullable
    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<Leave> leaves;

    @Nullable
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeePaymentDetails employeePaymentDetails;

}
