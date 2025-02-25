package org.ilvendev.profiles.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "employee_residence_details")
public class EmployeeResidenceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = false)
    private String country;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
