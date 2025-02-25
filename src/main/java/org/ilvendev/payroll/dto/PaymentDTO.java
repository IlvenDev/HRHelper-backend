package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentDTO {
    private int id;
    private String bankAccountNumber;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private LocalDate dueDate;
    private LocalDate paymentDate;
}
