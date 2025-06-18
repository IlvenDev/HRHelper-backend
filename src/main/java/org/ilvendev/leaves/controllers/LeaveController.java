package org.ilvendev.leaves.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.leaves.dto.LeaveRequest;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.leaves.services.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@Transactional
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    private LeaveService leaveService;

    @PostMapping("/request")
    public ResponseEntity<LeaveResponse> requestLeave(@Valid @RequestBody LeaveRequest request){
        log.debug("Received leave request");

        LeaveResponse createdLeave = leaveService.requestLeave(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLeave.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdLeave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changeLeaveStatus(@Valid @PathVariable("id") Integer leaveId, @Valid @RequestParam LeaveStatus newStatus){
        log.debug("Received leave status change request");

        leaveService.changeLeaveStatus(leaveId, newStatus);

        return ResponseEntity.ok(String.format("Status for leave %s changes successfully", leaveId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaveResponse>> getAllLeaves(){
        log.debug("Received get all leaves request");

        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<LeaveResponse>> getEmployeeLeaves(@Valid @PathVariable("id") Integer employeeId){
        log.debug("Received get employee leaves request");

        return ResponseEntity.ok(leaveService.getEmployeeLeaves(employeeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponse> getLeaveById(@Valid @PathVariable("id") Integer leaveId){
        log.debug("Received get leave by id request");

        return ResponseEntity.ok(leaveService.getLeaveById(leaveId));
    }

    @GetMapping("/get")
    public ResponseEntity<List<LeaveResponse>> getByParams(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LeaveType type,
            @RequestParam(required = false) LeaveStatus status,
            @RequestParam(required = false) Integer employeeId
    ) {
        log.debug("Received get all leaves with params: Date {}, Type {}, Status {}, EmployeeId {}", date, type, status, employeeId);

        List<LeaveResponse> fetchedLeaves;

        if (date != null) {
            fetchedLeaves = leaveService.getLeavesByDate(date);
        } else if (type != null) {
            fetchedLeaves = leaveService.getLeavesByType(type);
        } else if (status != null) {
            fetchedLeaves = leaveService.getLeavesByStatus(status);
        } else if (employeeId != null) {
            fetchedLeaves = leaveService.getLeavesByEmployeeId(employeeId);
        } else {
            fetchedLeaves = leaveService.getAllLeaves();
        }

        return ResponseEntity.ok(fetchedLeaves);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countLeavesInMonth(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(leaveService.countLeavesInMonth(year, month));
    }

    // GET /profiles/leaves/distribution?year=2025&month=6
    @GetMapping("/distribution")
    public ResponseEntity<Map<LeaveType, Long>> getLeaveTypeDistributionInMonth(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(leaveService.getLeaveTypeDistributionInMonth(year, month));
    }
}
