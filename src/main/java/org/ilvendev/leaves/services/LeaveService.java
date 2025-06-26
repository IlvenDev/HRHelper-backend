package org.ilvendev.leaves.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.dto.LeaveRequest;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.leaves.mappers.LeaveMapper;
import org.ilvendev.leaves.repositories.LeaveRepository;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    EmployeeRepository employeeRepository;

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

        fetchedLeave.setStatus(newStatus);
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

    public List<LeaveResponse> getLeavesByDate(LocalDate startDate, LocalDate endDate) {
        log.debug("Fetching leaves for date {}", startDate, endDate);

        List<Leave> leaves = leaveRepository.findByTimeframe(startDate, endDate);

        log.info("Successfully fetched leaves for date {}", startDate, endDate);
        return leaveMapper.toResponseList(leaves);
    }

    public List<LeaveResponse> getLeavesByStatus(LeaveStatus status) {
        log.debug("Fetching leaves with status {}", status);

        List<Leave> leaves = leaveRepository.findByStatus(status);

        log.info("Successfully fetched leaves with status {}", status);
        return leaveMapper.toResponseList(leaves);
    }

    public List<LeaveResponse> getLeavesByType(LeaveType type) {
        log.debug("Fetching leaves with type {}", type);

        List<Leave> leaves = leaveRepository.findByRodzaj(type);

        log.info("Successfully fetched leaves with type {}", type);
        return leaveMapper.toResponseList(leaves);
    }

    public List<LeaveResponse> getLeavesByEmployeeId(Integer employeeId) {
        log.debug("Fetching leaves for employee ID {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId.toString()));


        List<Leave> leaves = leaveRepository.findByEmployee(employee);

        log.info("Successfully fetched leaves for employee ID {}", employeeId);
        return leaveMapper.toResponseList(leaves);
    }

    public long countLeavesInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);
        return leaveRepository.countLeavesInMonth(start, end);
    }

    public Map<LeaveType, Long> getLeaveTypeDistributionInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);
        List<Object[]> results = leaveRepository.countLeavesByTypeInMonth(start, end);
        return results.stream()
                .collect(Collectors.toMap(
                        r -> (LeaveType) r[0],
                        r -> (Long) r[1]
                ));
    }

    public Map<LeaveType, Long> countLeaveDaysByTypes(Integer employeeId, List<LeaveType> types) {
        List<Leave> leaves = leaveRepository.findByEmployeeIdAndRodzajInAndStatus(employeeId, types, LeaveStatus.ZATWIERDZONE);

        Map<LeaveType, Long> result = new HashMap<>();
        for (LeaveType type : types) {
            result.put(type, 0L);
        }

        for (Leave leave : leaves) {
            long days = ChronoUnit.DAYS.between(leave.getDataStart(), leave.getDataKoniec()) + 1;
            result.put(leave.getRodzaj(), result.get(leave.getRodzaj()) + days);
        }
        return result;
    }


//    public List<LeaveResponse> getByDate();
}
