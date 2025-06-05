package org.ilvendev.leaves.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.leaves.dto.LeaveRequest;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.leaves.services.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
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
}
