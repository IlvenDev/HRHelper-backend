package org.ilvendev.attendance.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.attendance.dto.AttendanceTimeRequest;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.attendance.services.AttendanceTimeService;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendance")
@AllArgsConstructor
@Slf4j
@Validated
@Tag(name = "Attendance management", description = "API for attendance management")
public class AttendanceController {

    private AttendanceTimeService attendanceTimeService;
    private EmployeeRepository employeeRepository;

    @PostMapping("/initialize")
    @Operation(summary = "Initialized attendance for employee")
    public ResponseEntity<AttendanceTimeResponse> initializeAttendance(@Valid @RequestBody AttendanceTimeRequest request){
        log.debug("Received attendance initialization request");

        AttendanceTimeResponse initializedAttendance = attendanceTimeService.initializeAttendance(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(initializedAttendance.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(initializedAttendance);
    }

    @PutMapping("/finalize/{id}")
    @Operation(summary = "Finalize attendance with a given ID")
    public ResponseEntity<AttendanceTimeResponse> finalizeAttendance(@PathVariable("id") Integer id,
                                                                     @RequestParam LocalTime endTime){
        log.debug("Received attendance finalization request with ID: {}", id);

        AttendanceTimeResponse finalizedAttendance = attendanceTimeService.finalizeAttendance(id, endTime);

        return ResponseEntity.ok(finalizedAttendance);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all attendance records")
    public List<AttendanceTimeResponse> getAllRecords(){
        log.debug("Received get all attendance request");

        return attendanceTimeService.getAllRecords();
    }



    @GetMapping("/employee/{id}")
    @Operation(summary = "Get attendance records for a certain employee")
    public List<AttendanceTimeResponse> getForEmployee(@PathVariable("id") Integer employeeId){
        log.debug("Received get employee attendance records request");

        return attendanceTimeService.getForEmployee(employeeId);
    }

    // TODO: Make it fucking better

    @GetMapping("/date")
    public List<AttendanceTimeResponse> getForDateBetween(@RequestParam LocalDate startDate,
                                                          @RequestParam LocalDate endDate){
        return attendanceTimeService.getByDateBetween(startDate, endDate);
    }

    @GetMapping("/employee")
    public List<AttendanceTimeResponse> getForEmployeeAndDateBetween(@RequestParam Integer employeeId,
                                                                     @RequestParam LocalDate startDate,
                                                                     @RequestParam LocalDate endDate){
        log.debug("Received get employee attendance records for date range request");

        Employee fetchedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId.toString()));

        return attendanceTimeService.getByEmployeeAndDateBetween(fetchedEmployee, startDate, endDate);
    }
}
