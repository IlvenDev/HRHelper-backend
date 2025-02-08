package org.ilvendev.api.payroll.dto;

import lombok.Data;
import org.ilvendev.api.profiles.dto.EmployeeDTO;
import org.ilvendev.enums.PayFrequency;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

@Data
public class EmployeePaymentDetailsDTO {
    private int id;
    private String bankName;
    private BigDecimal baseSalary;
    private Currency currency;
    private PayFrequency payFrequency;
    private String bankAccountNumber;
    private EmployeeDTO employee;
    private Set<PaymentDTO> payments;
}
