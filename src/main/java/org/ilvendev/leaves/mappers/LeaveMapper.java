package org.ilvendev.leaves.mappers;

import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.dto.LeaveRequest;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.mappers.EmployeeMapper;
import org.ilvendev.profiles.services.EmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {EmployeeMapper.class})
public abstract class LeaveMapper {

    @Autowired
    private EmployeeService employeeService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", source = "employeeId")
    @Mapping(target = "status", constant = "OCZEKUJĄCE")
    public abstract Leave toEntity(LeaveRequest request);

    @Mapping(source = "employee", target = "employee") // użyje EmployeeMapper
    public abstract LeaveResponse toResponse(Leave leave);

    public abstract List<LeaveResponse> toResponseList(List<Leave> leaves);

    public Employee mapEmployeeIdToEmployee(Integer employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeService.findById(employeeId);
    }
}
