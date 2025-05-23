package org.ilvendev.payroll.repositories;

import org.ilvendev.payroll.domain.EmployeePaymentDetails;
import org.ilvendev.payroll.dto.EmployeePaymentDetailsResponseBasic;
import org.ilvendev.profiles.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePaymentDetailsRepository extends JpaRepository<EmployeePaymentDetails, Integer> {
    List<EmployeePaymentDetailsResponseBasic> findByBankAccountNumber(String bankAccountNumber);

    List<EmployeePaymentDetailsResponseBasic> findByEmployee(Employee employee);
}
