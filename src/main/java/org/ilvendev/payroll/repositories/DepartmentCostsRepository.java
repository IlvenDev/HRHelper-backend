package org.ilvendev.payroll.repositories;

import org.ilvendev.payroll.domain.DepartmentCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentCostsRepository extends JpaRepository<DepartmentCosts, Integer> {
}
