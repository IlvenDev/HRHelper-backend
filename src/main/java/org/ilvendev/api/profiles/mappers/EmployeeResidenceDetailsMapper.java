package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.dto.EmployeeResidenceDetailsDTO;
import org.ilvendev.database.profiles.EmployeeResidenceDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeResidenceDetailsMapper {

    EmployeeResidenceDetailsDTO employeeResidenceDetailsToEmployeeResidenceDetailsDTO(EmployeeResidenceDetails employeeResidenceDetails);

    EmployeeResidenceDetails employeeResidenceDetailsDTOToEmployeeResidenceDetails(EmployeeResidenceDetailsDTO employeeResidenceDetailsDTO);
}
