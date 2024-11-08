package org.ilvendev.payroll;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ilvendev.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bankAccountNumber;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "employeeBankAccount")
    private EmployeePaymentDetails employeePaymentDetails;
}
