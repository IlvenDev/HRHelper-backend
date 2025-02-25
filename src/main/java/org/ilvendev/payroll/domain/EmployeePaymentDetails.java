package org.ilvendev.payroll.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ilvendev.enums.PayFrequency;
import org.ilvendev.profiles.domain.Employee;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee_payment_details")
public class EmployeePaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bankName;
    private BigDecimal baseSalary;
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private PayFrequency payFrequency;
    private String bankAccountNumber;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @OneToMany(mappedBy = "employeePaymentDetails", cascade = CascadeType.PERSIST)
    private Set<Payment> payments;

}
