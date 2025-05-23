package org.ilvendev.payroll.dto;

import org.ilvendev.payroll.domain.EmployeePaymentDetails;

import java.time.LocalDate;

public class PaymentResponseDetail extends PaymentResponseBasic{
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private EmployeePaymentDetails employeePaymentDetails;
}
