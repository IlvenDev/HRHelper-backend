package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.EmployeeJobDetailsDTO;
import org.ilvendev.api.profiles.EmployeeResidenceDetailsDTO;
import org.ilvendev.database.profiles.EmployeeJobDetails;
import org.ilvendev.database.profiles.EmployeeResidenceDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeResidenceDetailsMapper {

    EmployeeResidenceDetailsMapper INSTANCE = Mappers.getMapper(EmployeeResidenceDetailsMapper.class);

    EmployeeResidenceDetailsDTO employeeResidenceDetailsToEmployeeResidenceDetailsDTO(EmployeeResidenceDetails employeeResidenceDetails);

    EmployeeResidenceDetails employeeResidenceDetailsDTOToEmployeeResidenceDetails(EmployeeResidenceDetailsDTO employeeResidenceDetailsDTO);
}
