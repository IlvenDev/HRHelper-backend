package org.ilvendev.payroll.mappers;

import org.ilvendev.payroll.domain.EmployeePaymentDetails;
import org.ilvendev.payroll.dto.EmployeePaymentDetailsRequest;
import org.ilvendev.payroll.dto.EmployeePaymentDetailsResponseBasic;
import org.ilvendev.payroll.dto.EmployeePaymentDetailsResponseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmployeePaymentDetailsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankName")
    @Mapping(target = "baseSalary")
    @Mapping(target = "currency")
    @Mapping(target = "payFrequency")
    @Mapping(target = "bankAccountNumber")
    @Mapping(target = "employee", ignore = true)
    EmployeePaymentDetails toEntity(EmployeePaymentDetailsRequest request);

    EmployeePaymentDetailsResponseDetail toDetailResponse(EmployeePaymentDetails paymentDetails);

    EmployeePaymentDetailsResponseBasic toBasicResponse(EmployeePaymentDetails paymentDetails);
}
