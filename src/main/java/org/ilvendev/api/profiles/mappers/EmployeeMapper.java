package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.dto.EmployeeDTO;
import org.ilvendev.database.profiles.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}


