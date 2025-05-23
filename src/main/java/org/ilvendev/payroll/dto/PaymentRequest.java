package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

// TODO: Add validation

@Data
public class PaymentRequest {
    private String bankAccountNumber;
    private BigDecimal amount;
    private Currency currency;
    private PaymentStatus status;
    private LocalDate dueDate;
    private LocalDate paymentDate;
}
