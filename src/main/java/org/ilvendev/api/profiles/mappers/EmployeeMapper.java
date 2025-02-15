package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.dto.*;
import org.ilvendev.database.profiles.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO mapToDTO(Employee employee);

    Employee mapToEmployee(EmployeeDTO employeeDTO);
}
