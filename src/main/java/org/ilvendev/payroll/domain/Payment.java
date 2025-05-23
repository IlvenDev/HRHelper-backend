package org.ilvendev.payroll.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bankAccountNumber;
    private BigDecimal amount;
    private Currency currency;
    private PaymentStatus status;
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;
    // Timestamp might be better here, to make it clear when exactly the payment was made
    @Temporal(TemporalType.DATE)
    private LocalDate paymentDate;
}
