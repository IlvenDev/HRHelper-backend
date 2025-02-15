package org.ilvendev.api.attendance.dto;

import lombok.Data;
import org.ilvendev.api.profiles.dto.EmployeeDTO;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceTimeDTO {
    private Integer id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private EmployeeDTO employee;
}
