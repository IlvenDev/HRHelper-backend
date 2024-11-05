package org.ilvendev.profiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_residence_details")
public class EmployeeResidenceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int residenceId;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
