package org.ilvendev.api.attendance.dto;

import lombok.Data;
import org.ilvendev.api.profiles.dto.EmployeeDTO;

import java.sql.Time;

@Data
public class AttendanceTimeDTO {
    private int id;
    private Time startTime;
    private Time endTime;
    private EmployeeDTO employee;
}
