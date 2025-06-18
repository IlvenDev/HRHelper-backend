package org.ilvendev.leaves.mappers;

import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.dto.LeaveRequest;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.services.EmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class LeaveMapper {

    @Autowired
    private EmployeeService employeeService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "leaveStatus", constant = "PENDING")
    @Mapping(target = "employee", source = "employeeId")
    public abstract Leave toEntity(LeaveRequest request);

    public abstract LeaveResponse toResponse(Leave leave);

    public abstract List<LeaveResponse> toResponseList(List<Leave> leaves);

    public Employee mapEmployeeIdToEmployee(Integer employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeService.findById(employeeId);
    }
}
