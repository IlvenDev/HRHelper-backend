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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
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

}
