package org.ilvendev.api.attendance.exceptions;

public class AttendanceTimeNotFoundException extends RuntimeException {
    public AttendanceTimeNotFoundException(Integer attendanceId) {
        super("Attendance time with ID " + attendanceId + " could not be found");
    }
}
