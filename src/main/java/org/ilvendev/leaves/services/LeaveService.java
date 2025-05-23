package org.ilvendev.leaves.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.dto.LeaveRequest;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.leaves.mappers.LeaveMapper;
import org.ilvendev.leaves.repositories.LeaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class LeaveService {
    // Request leave
    // change leave status

    // get all leaves
    // get leaves for employee
    // get leave by id

    // could also add searching by date, status, type

    LeaveRepository leaveRepository;
    LeaveMapper leaveMapper;

    // Think about how to check if isn't already in the DB
    public LeaveResponse requestLeave(LeaveRequest request){
        log.debug("Creating a leave request");

        Leave savedLeave = leaveRepository.save(leaveMapper.toEntity(request));

        log.info("Successfully requested leave");
        return leaveMapper.toResponse(savedLeave);
    }

    public void changeLeaveStatus(Integer id, LeaveStatus newStatus){
        log.debug("Changing status for leave with ID: {}", id);
        Leave fetchedLeave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave", id.toString()));

        fetchedLeave.setLeaveStatus(newStatus);
        log.info("Changed leave status");
    }

    public List<LeaveResponse> getAllLeaves(){
        log.debug("Getting all leaves");
        return leaveRepository.findAll()
                .stream()
                .map(leaveMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<LeaveResponse> getEmployeeLeaves(Integer id){
        log.debug("Getting leaves for employee with ID: {}", id.toString());
        return leaveRepository.findByEmployee(leaveMapper.mapEmployeeIdToEmployee(id))
                .stream()
                .map(leaveMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LeaveResponse getLeaveById(Integer id){
        log.debug("Getting leave with ID: {}", id.toString());
        Leave fetchedLeave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave", id.toString()));

        return leaveMapper.toResponse(fetchedLeave);
    }

//    public List<LeaveResponse> getByDate();
}
