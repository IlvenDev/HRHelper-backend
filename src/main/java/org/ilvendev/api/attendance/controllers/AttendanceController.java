package org.ilvendev.api.attendance.controllers;

import lombok.AllArgsConstructor;
import org.ilvendev.api.attendance.dto.AttendanceTimeDTO;
import org.ilvendev.api.attendance.exceptions.AttendanceTimeNotFoundException;
import org.ilvendev.api.attendance.services.AttendanceTimeService;
import org.ilvendev.api.profiles.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/attendance")
@AllArgsConstructor
public class AttendanceController {

    private AttendanceTimeService attendanceTimeService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeAttendance(@RequestBody AttendanceTimeDTO attendanceData){
        Integer initializedAttendanceId = attendanceTimeService.initializeAttendance(attendanceData);

        return ResponseEntity.ok("Initialized attendance with ID: " + initializedAttendanceId);
    }

    @PutMapping("/finalize")
    public ResponseEntity<String> finalizeAttendance(@RequestParam Integer attendanceId, @RequestParam LocalTime endTime){
        try {
            attendanceTimeService.finalizeAttendance(attendanceId, endTime);
            return ResponseEntity.ok("Finalized attendance with ID: " + attendanceId);
        } catch (AttendanceTimeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<AttendanceTimeDTO>> getAllAttendance(){
        return ResponseEntity.ok(attendanceTimeService.getAllAttendance());
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<List<AttendanceTimeDTO>> getAttendanceByEmployeeId(@RequestParam Integer employeeId){
        try {
            return ResponseEntity.ok().body(attendanceTimeService.getAttendanceByEmployeeId(employeeId));
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get_date_range")
    public ResponseEntity<List<AttendanceTimeDTO>> getAttendanceByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(attendanceTimeService.getAttendanceByDateRange(startDate, endDate));
    }

    public ResponseEntity<List<AttendanceTimeDTO>> getAttendanceByEmployeeIdAndDateRange(@RequestParam Integer employeeId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        try {
            return ResponseEntity.ok(attendanceTimeService.getAttendanceByEmployeeIdAndDateRange(employeeId, startDate, endDate));
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
