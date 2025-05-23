package org.ilvendev.payroll.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ilvendev.profiles.domain.Employee;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeePaymentDetailsResponseDetail extends EmployeePaymentDetailsResponseBasic{
    private Employee employee;
}
