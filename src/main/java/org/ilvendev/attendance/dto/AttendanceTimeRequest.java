package org.ilvendev.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.ilvendev.profiles.domain.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceTimeRequest {
    @NotBlank(message = "Start time is required")
    @PastOrPresent(message = "Start time cannot be in the future")
    private LocalTime startTime;
    @PastOrPresent(message = "End time cannot be in the future")
    private LocalTime endTime;
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    @NotBlank(message = "Employee is required")
    private Employee employee;
}
