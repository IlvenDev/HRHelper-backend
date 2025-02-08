package org.ilvendev.api.payroll.mappers;

import org.ilvendev.api.payroll.dto.PaymentsDTO;
import org.ilvendev.database.payroll.Payments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {

    PaymentsDTO paymentsToPaymentsDTO (Payments payments);

    Payments paymentsDTOToPayments (PaymentsDTO paymentsDTO);
}
