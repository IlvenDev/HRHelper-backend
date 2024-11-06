package org.ilvendev.profiles.emergency_contact;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.profiles.employee.Employee;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String relationship; // This could use a new validation table "relationships" and a ENUM
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
