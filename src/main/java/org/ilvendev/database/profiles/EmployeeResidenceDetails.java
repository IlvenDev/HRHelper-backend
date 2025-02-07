package org.ilvendev.database.profiles;

import jakarta.persistence.*;
import lombok.*;

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
