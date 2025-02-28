package org.ilvendev.profiles.mappers;

import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.payroll.domain.EmployeePaymentDetails;
import org.ilvendev.profiles.domain.EmergencyContact;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.domain.EmployeeJobDetails;
import org.ilvendev.profiles.domain.EmployeeResidenceDetails;
import org.ilvendev.profiles.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attendanceTimes", ignore = true)
    @Mapping(target = "leaves", ignore = true)
    @Mapping(target = "emergencyContact", ignore = true)
    @Mapping(target = "jobDetails", ignore = true)
    @Mapping(target = "residenceDetails", ignore = true)
    @Mapping(target = "employeePaymentDetails", ignore = true)
    Employee toEntity(EmployeeRequest request);

    EmployeeBasicResponse toBasicResponse(Employee employee);

    List<EmployeeBasicResponse> toBasicResponseList(List<Employee> employees);

    @Mapping(target = "emergencyContact", source = "emergencyContact")
    @Mapping(target = "jobDetails", source = "jobDetails")
    @Mapping(target = "residenceDetails", source = "residenceDetails")
    @Mapping(target = "employeePaymentDetails", source = "employeePaymentDetails")
    EmployeeDetailResponse toDetailResponse(Employee employee);

    @Mapping(target = "emergencyContact", source = "emergencyContact")
    @Mapping(target = "jobDetails", source = "jobDetails")
    @Mapping(target = "residenceDetails", source = "residenceDetails")
    @Mapping(target = "employeePaymentDetails", source = "employeePaymentDetails")
    @Mapping(target = "attendanceTimes", source = "attendanceTimes")
    @Mapping(target = "leaves", source = "leaves")
    EmployeeAdminResponse toAdminResponse(Employee employee);

    EmergencyContactResponse toEmergencyContactResponse(EmergencyContact emergencyContact);
    EmployeeJobDetailsResponse toJobDetailsResponse(EmployeeJobDetails jobDetails);
    EmployeeResidenceDetailsResponse toResidenceDetailsResponse(EmployeeResidenceDetails residenceDetails);
    EmployeePaymentDetailsResponse toPaymentDetailsResponse(EmployeePaymentDetails paymentDetails);
    AttendanceTimeResponse toAttendanceTimeResponse(AttendanceTime attendanceTime);
    LeaveResponse toLeaveResponse(Leave leave);
}
