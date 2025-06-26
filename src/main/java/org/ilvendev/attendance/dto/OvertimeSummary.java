package org.ilvendev.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OvertimeSummary {
    private long regularMinutes;
    private long nightMinutes;
    private long sundayHolidayMinutes;
}