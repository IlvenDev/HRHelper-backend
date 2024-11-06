package org.ilvendev.attendance;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.profiles.employee.Employee;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "attendance_times")
public class AttendanceTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIME)
    private Time startTime;

    @Temporal(TemporalType.TIME)
    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

}
