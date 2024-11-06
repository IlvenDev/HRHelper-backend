package org.ilvendev.profiles.residence_details;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.profiles.employee.Employee;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee_residence_details")
public class EmployeeResidenceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
