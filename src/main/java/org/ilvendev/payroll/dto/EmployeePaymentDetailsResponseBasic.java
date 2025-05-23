package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.PayFrequency;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class EmployeePaymentDetailsResponseBasic {
    private Integer id;
    private String bankName;
    private BigDecimal baseSalary;
    private Currency currency;
    private PayFrequency payFrequency;
    private String bankAccountNumber;
}
