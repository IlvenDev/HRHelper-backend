package org.ilvendev.payroll.repositories;

import org.ilvendev.enums.PaymentStatus;
import org.ilvendev.payroll.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByBankAccountNumber(String bankAccountNumber);

    List<Payment> findByStatus(PaymentStatus status);

    // Might want to turn this into like a before this date
    List<Payment> findByDueDate(LocalDate dueDate);

    List<Payment> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}
