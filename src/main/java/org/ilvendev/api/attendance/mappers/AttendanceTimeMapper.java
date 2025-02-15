package org.ilvendev.api.attendance.mappers;

import org.ilvendev.api.attendance.dto.AttendanceTimeDTO;
import org.ilvendev.api.profiles.dto.EmployeeDTO;
import org.ilvendev.api.profiles.repositories.EmployeeRepository;
import org.ilvendev.database.attendance.AttendanceTime;
import org.ilvendev.database.profiles.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface AttendanceTimeMapper {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;  // Inject repository to fetch existing employee

//    @Mapping(target = "employee", source = "employee")
//    public abstract AttendanceTime mapToAttendanceTime(AttendanceTimeDTO attendanceTimeDTO);
//
//    public abstract AttendanceTimeDTO mapToDTO(AttendanceTime attendanceTime);
//
//    public Employee mapEmployee(Employee employee) {
//        if (employee == null) {
//            return null;
//        }
//        if (employee.getId() != null) {
//            // Fetch existing employee from DB
//            return employeeRepository.findById(employee.getId())
//                    .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employee.getId()));
//        }
//        return employee;  // If ID is null, assume it's a new employee
//    }

    public AttendanceTime mapToAttendanceTime(AttendanceTimeDTO attendanceTimeDTO);

    public AttendanceTimeDTO mapToDTO(AttendanceTime attendanceTime);
}
