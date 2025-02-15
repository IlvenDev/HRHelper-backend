package org.ilvendev.database.attendance;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.database.profiles.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "attendance_times")
public class AttendanceTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalTime startTime;

    @Nullable
    private LocalTime endTime;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

}
