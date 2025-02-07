package org.ilvendev.database.attendance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ilvendev.database.profiles.Employee;
import org.ilvendev.database.enums.LeaveStatus;
import org.ilvendev.database.enums.LeaveType;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "leaves")
public class Leaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
