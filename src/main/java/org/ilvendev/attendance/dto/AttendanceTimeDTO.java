package org.ilvendev.attendance.dto;

import lombok.Data;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceTimeDTO {
    private Integer id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private EmployeeBasicResponse employee;
}
