package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.PayFrequency;
import org.ilvendev.enums.ValueOfEnum;

import java.math.BigDecimal;
import java.util.Currency;

// TODO: Add some validation, don't know that tho

@Data
public class EmployeePaymentDetailsRequest {
    private String bankName;
    private BigDecimal baseSalary;
    private Currency currency;
    @ValueOfEnum(enumClass = PayFrequency.class)
    private PayFrequency payFrequency;
    private String bankAccountNumber;
}
