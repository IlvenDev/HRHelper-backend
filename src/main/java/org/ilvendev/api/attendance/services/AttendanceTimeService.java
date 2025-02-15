package org.ilvendev.api.attendance.services;

import lombok.AllArgsConstructor;
import org.ilvendev.api.attendance.exceptions.AttendanceTimeNotFoundException;
import org.ilvendev.api.attendance.dto.AttendanceTimeDTO;
import org.ilvendev.api.attendance.mappers.AttendanceTimeMapper;
import org.ilvendev.api.attendance.repositories.AttendanceTimeRepository;
import org.ilvendev.api.profiles.exceptions.EmployeeNotFoundException;
import org.ilvendev.api.profiles.repositories.EmployeeRepository;
import org.ilvendev.database.attendance.AttendanceTime;
import org.ilvendev.database.profiles.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public Integer initializeAttendance(AttendanceTimeDTO attendanceData){
        return attendanceTimeRepository.save(attendanceTimeMapper.mapToAttendanceTime(attendanceData)).getId();
    }

    public void finalizeAttendance(Integer attendanceId, LocalTime endTime) throws AttendanceTimeNotFoundException{
        AttendanceTime fetchedAttendance = attendanceTimeRepository.findById(attendanceId)
                .orElseThrow(() -> new AttendanceTimeNotFoundException(attendanceId));

        fetchedAttendance.setEndTime(endTime);

        attendanceTimeRepository.save(fetchedAttendance);
    }

    public List<AttendanceTimeDTO> getAllAttendance(){
        return attendanceTimeRepository.findAll()
                .stream()
                .map(attendanceTimeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceTimeDTO> getAttendanceByEmployeeId(Integer employeeId) throws EmployeeNotFoundException{
        Employee fetchedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        return attendanceTimeRepository.findByEmployee(fetchedEmployee)
                .stream()
                .map(attendanceTimeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceTimeDTO> getAttendanceByDateRange(LocalDate startDate, LocalDate endDate){
        return attendanceTimeRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(attendanceTimeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceTimeDTO> getAttendanceByEmployeeIdAndDateRange(Integer employeeId, LocalDate startDate, LocalDate endDate) throws EmployeeNotFoundException{
        Employee fetchedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        return attendanceTimeRepository.findByEmployeeAndDateBetween(fetchedEmployee, startDate, endDate)
                .stream()
                .map(attendanceTimeMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
