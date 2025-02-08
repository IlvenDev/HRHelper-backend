package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.dto.EmployeeJobDetailsDTO;
import org.ilvendev.database.profiles.EmployeeJobDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeJobDetailsMapper {

    EmployeeJobDetailsDTO employeeJobDetailsToEmployeeJobDetailsDTO(EmployeeJobDetails employeeJobDetails);

    EmployeeJobDetails employeeJobDetailsDTOToEmployeeJobDetails(EmployeeJobDetailsDTO employeeJobDetailsDTO);
}
