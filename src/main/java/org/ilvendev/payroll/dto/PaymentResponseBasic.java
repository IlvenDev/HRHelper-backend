package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class PaymentResponseBasic {
    private Integer id;
    private String bankAccountNumber;
    private BigDecimal amount;
    private Currency currency;
    private PaymentStatus status;
}
