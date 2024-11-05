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
@Table(name = "emergency_contacts")
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emergencyContactId;
    private String name;
    private String lastname;
    private String relationship; // This could use a new validation table "relationships" and a ENUM
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
