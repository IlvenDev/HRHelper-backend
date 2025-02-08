package org.ilvendev.api.payroll.mappers;

import org.ilvendev.api.payroll.dto.PaymentDTO;
import org.ilvendev.database.payroll.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO paymentToPaymentDTO (Payment payment);

    Payment paymentDTOToPayment (PaymentDTO paymentDTO);
}
