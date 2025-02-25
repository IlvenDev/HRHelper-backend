package org.ilvendev.attendance.services;

import lombok.AllArgsConstructor;
import org.ilvendev.attendance.mappers.AttendanceTimeMapper;
import org.ilvendev.attendance.repositories.AttendanceTimeRepository;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttendanceTimeService {

    private final AttendanceTimeRepository attendanceTimeRepository;
    private final AttendanceTimeMapper attendanceTimeMapper;
    private final EmployeeRepository employeeRepository;

    // init attendance
    // fin attendance
    // get all
    // get for employee
    // get from date to date (no employee)
    // get for employee from date to date

}
