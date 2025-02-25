package org.ilvendev.leaves.dto;

import lombok.Data;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;

import java.util.Date;

@Data
public class LeaveDTO {
    private int id;
    private Date date;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
    private EmployeeBasicResponse employee;
}