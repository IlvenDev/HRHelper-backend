package org.ilvendev.attendance.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.dto.AttendanceTimeRequest;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.attendance.mappers.AttendanceTimeMapper;
import org.ilvendev.attendance.repositories.AttendanceTimeRepository;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
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

    // need to add updating but only when admin

    // Need to add checking if exists
    @Transactional
    public AttendanceTimeResponse initializeAttendance(AttendanceTimeRequest request){
        log.debug("Initializing attendance request");

        AttendanceTime attendanceToSave = attendanceTimeMapper.toEntity(request);

        AttendanceTimeResponse initializedAttendance = attendanceTimeMapper.toResponse(attendanceTimeRepository.save(attendanceToSave));
        log.info("Initialized attendance request with ID: {}", initializedAttendance);

        return initializedAttendance;
    }

    @Transactional
    public AttendanceTimeResponse finalizeAttendance(Integer id, LocalTime endTime){
        log.debug("Finalizing attendance request");

        AttendanceTime fetchedAttendance = attendanceTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance time", id.toString()));

        fetchedAttendance.setEndTime(endTime);

        AttendanceTime finalizedAttendance = attendanceTimeRepository.save(fetchedAttendance);

        return attendanceTimeMapper.toResponse(finalizedAttendance);
    }

    public List<AttendanceTimeResponse> getAllRecords(){
        log.debug("Fetching all attendance records");

        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findAll());
    }

    // Might be good to add empty list checking, but that could be in the controller or on the frontend
    public List<AttendanceTimeResponse> getForEmployee(Integer id){
        log.debug("Fetching employee {} attendance records", id);

        Employee fetchedEmployee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id.toString()));

        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findByEmployee(fetchedEmployee));
    }

    public List<AttendanceTimeResponse> getByDateBetween(LocalDate startDate, LocalDate endDate){
        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findByDateBetween(startDate, endDate));
    }

    public List<AttendanceTimeResponse> getByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate){
        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findByEmployeeAndDateBetween(employee, startDate, endDate));
    }
}
