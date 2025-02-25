package org.ilvendev.payroll.repositories;

import org.ilvendev.payroll.domain.EmployeePaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePaymentDetailsRepository extends JpaRepository<EmployeePaymentDetails, Integer> {
}
