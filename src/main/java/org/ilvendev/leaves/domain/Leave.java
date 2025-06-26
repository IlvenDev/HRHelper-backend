package org.ilvendev.leaves.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Should probably make it a date range, as the leave can be a vacation so could be some time
    @Column
    private LocalDate dataStart;
    @Column
    private LocalDate dataKoniec;
    @Column
    @Enumerated(EnumType.STRING)
    private LeaveType rodzaj;
    @Column
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Column
    @CreationTimestamp
    private LocalDate złożono;

    // TODO: Should probably extend it with more data like reason

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
