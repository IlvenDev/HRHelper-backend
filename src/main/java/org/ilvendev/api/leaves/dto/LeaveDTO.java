package org.ilvendev.api.leaves.dto;

import lombok.Data;
import org.ilvendev.api.profiles.dto.EmployeeDTO;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;

import java.util.Date;

@Data
public class LeaveDTO {
    private int id;
    private Date date;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
    private EmployeeDTO employee;
}