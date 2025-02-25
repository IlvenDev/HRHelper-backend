package org.ilvendev.payroll.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ilvendev.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bankAccountNumber;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;
    @Temporal(TemporalType.DATE)
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "employeeBankAccount")
    private EmployeePaymentDetails employeePaymentDetails;
}
