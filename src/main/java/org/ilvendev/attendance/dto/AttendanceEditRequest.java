package org.ilvendev.attendance.dto;

import lombok.Data;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceEditRequest {
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean breakTaken;
    private LocalDate date;
}
