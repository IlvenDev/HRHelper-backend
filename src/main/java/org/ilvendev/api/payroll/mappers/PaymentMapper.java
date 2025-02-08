package org.ilvendev.api.payroll.mappers;

import org.ilvendev.api.payroll.dto.PaymentDTO;
import org.ilvendev.database.payroll.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO mapToDTO (Payment payment);

    Payment mapToPayment (PaymentDTO paymentDTO);
}
