package org.ilvendev.api.payroll.mappers;

import org.ilvendev.api.payroll.dto.EmployeePaymentDetailsDTO;
import org.ilvendev.database.payroll.EmployeePaymentDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeePaymentDetailsMapper {

    EmployeePaymentDetailsDTO employeePaymentDetailsToEmployeePaymentDetailsDTO (EmployeePaymentDetails employeePaymentDetails);

    EmployeePaymentDetails employeePaymentDetailsDTOToEmployeePaymentDetails (EmployeePaymentDetailsDTO employeePaymentDetailsDTO);
}
