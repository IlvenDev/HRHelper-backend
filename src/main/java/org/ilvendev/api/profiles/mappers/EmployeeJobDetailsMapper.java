package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.EmployeeJobDetailsDTO;
import org.ilvendev.database.profiles.EmployeeJobDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeJobDetailsMapper {

    EmployeeJobDetailsMapper INSTANCE = Mappers.getMapper(EmployeeJobDetailsMapper.class);

    EmployeeJobDetailsDTO employeeJobDetailsToEmployeeJobDetailsDTO(EmployeeJobDetails employeeJobDetails);

    EmployeeJobDetails employeeJobDetailsDTOToEmployeeJobDetails(EmployeeJobDetailsDTO employeeJobDetailsDTO);
}
