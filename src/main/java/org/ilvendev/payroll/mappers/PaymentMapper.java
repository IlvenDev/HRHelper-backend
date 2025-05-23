package org.ilvendev.payroll.mappers;

import org.ilvendev.payroll.domain.Payment;
import org.ilvendev.payroll.dto.PaymentRequest;
import org.ilvendev.payroll.dto.PaymentResponseBasic;
import org.ilvendev.payroll.dto.PaymentResponseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankAccountNumber")
    @Mapping(target = "amount")
    @Mapping(target = "currency")
    @Mapping(target = "status")
    @Mapping(target = "dueDate")
    @Mapping(target = "paymentDate")
    Payment toEntity(PaymentRequest request);

    List<PaymentResponseBasic> toBasicResponseList(List<Payment> paymentList);

    PaymentResponseDetail toDetailResponse(Payment payment);
}
