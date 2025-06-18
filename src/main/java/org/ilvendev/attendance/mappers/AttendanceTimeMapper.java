package org.ilvendev.attendance.mappers;

import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.dto.AttendanceTimeRequest;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.services.EmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class AttendanceTimeMapper {

    @Autowired
    private EmployeeService employeeService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTime")
    @Mapping(target = "endTime")
    @Mapping(target = "date")
    @Mapping(target = "employee", source = "employeeId", qualifiedByName = "mapIdToEmployee")
    public abstract AttendanceTime toEntity(AttendanceTimeRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "employee", source = "employee")
    public abstract AttendanceTimeResponse toResponse(AttendanceTime attendanceTime);

    public abstract List<AttendanceTimeResponse> toResponseList(List<AttendanceTime> attendanceTimeList);

    @Named("mapIdToEmployee")
    public Employee mapIdToEmployee(Integer employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeService.findById(employeeId);
    }

}
