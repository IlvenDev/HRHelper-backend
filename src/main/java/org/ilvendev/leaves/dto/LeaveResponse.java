package org.ilvendev.leaves.dto;

import lombok.Data;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.profiles.domain.Employee;

import java.time.LocalDate;

@Data
public class LeaveResponse {
    private Integer id;
    private LocalDate date;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
    private Employee employee;
}
