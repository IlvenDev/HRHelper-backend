package org.ilvendev.attendance.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.profiles.domain.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attendance_times")
public class AttendanceTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column
    private LocalTime endTime;
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

}
