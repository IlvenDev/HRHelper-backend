package org.ilvendev.profiles.mappers;

import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.dto.LeaveResponse;
import org.ilvendev.payroll.domain.EmployeePaymentDetails;
import org.ilvendev.payroll.dto.EmployeePaymentDetailsResponseDetail;
import org.ilvendev.profiles.domain.*;
import org.ilvendev.profiles.dto.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class EmployeeMapper {

    @Autowired PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attendanceTimes", ignore = true)
    @Mapping(target = "leaves", ignore = true)
    @Mapping(source = "jobDetails", target = "jobDetails")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "dataZatrudnienia", target = "dataZatrudnienia")
    @Mapping(source = "dataZwolnienia", target = "dataZwolnienia")
    @Mapping(source = "stawka", target = "stawka")
    @Mapping(source = "wymiarPracy", target = "wymiarPracy")
    @Mapping(source = "rodzajRozliczenia", target = "rodzajRozliczenia")
    @Mapping(target = "staż", constant = "0")
    public abstract Employee toEntity(EmployeeRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "directSupervisor", ignore = true)
    @Mapping(target = "employee", ignore = true)
    public abstract EmployeeJobDetails toJobDetailsEntity(EmployeeJobDetailsRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    public abstract EmployeeResidenceDetails toResidenceDetailsEntity(EmployeeResidenceDetailsRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    public abstract EmergencyContact toEmergencyContactEntity(EmergencyContactRequest request);

    @Mapping(source = "dostępneDniUrlopu", target = "dostępneDniUrlopu")
    @Mapping(source = "wykorzystaneDniUrlopu", target = "wykorzystaneDniUrlopu")
    public abstract EmployeeBasicResponse toBasicResponse(Employee employee);
    public abstract List<EmployeeBasicResponse> toBasicResponseList(List<Employee> employees);

////    @Mapping(target = "emergencyContact", source = "emergencyContact")
////    @Mapping(target = "jobDetails", source = "jobDetails")
////    @Mapping(target = "residenceDetails", source = "residenceDetails")
//    public abstract EmployeeDetailResponse toDetailResponse(Employee employee);

//    @Mapping(target = "emergencyContact", source = "emergencyContact")
//    @Mapping(target = "jobDetails", source = "jobDetails")
//    @Mapping(target = "residenceDetails", source = "residenceDetails")
//    @Mapping(target = "attendanceTimes", source = "attendanceTimes")
//    @Mapping(target = "leaves", source = "leaves")
//    public abstract EmployeeAdminResponse toAdminResponse(Employee employee);

    public abstract EmergencyContactResponse toEmergencyContactResponse(EmergencyContact emergencyContact);
    public abstract EmployeeJobDetailsResponse toJobDetailsResponse(EmployeeJobDetails jobDetails);
    public abstract EmployeeResidenceDetailsResponse toResidenceDetailsResponse(EmployeeResidenceDetails residenceDetails);

    @Mapping(target = "employee", source = "employee")
    public abstract AttendanceTimeResponse toAttendanceTimeResponse(AttendanceTime attendanceTime);
//    public abstract LeaveResponse toLeaveResponse(Leave leave);

    public Integer mapEmployeeToEmployeeId(Employee employee) {
        if (employee == null) {
            return null;
        }
        return employee.getId();
    }

    // Uncomment if needed later
    // EmployeePaymentDetailsResponseDetail toPaymentDetailsResponse(EmployeePaymentDetails paymentDetails);
}
