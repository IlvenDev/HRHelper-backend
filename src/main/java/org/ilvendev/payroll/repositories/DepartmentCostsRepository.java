package org.ilvendev.payroll.repositories;

import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;
import org.ilvendev.payroll.domain.DepartmentCosts;
import org.ilvendev.payroll.dto.DepartmentCostsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartmentCostsRepository extends JpaRepository<DepartmentCosts, Integer> {
    List<DepartmentCosts> findByDepartment(Department department);

    List<DepartmentCosts> findByDate(LocalDate date);

    List<DepartmentCosts> findByCostType(CostType costType);

    List<DepartmentCosts> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(c.amount) FROM DepartmentCosts c WHERE c.date >= :start AND c.date < :end")
    BigDecimal sumCostsInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT c.costType, SUM(c.amount) FROM DepartmentCosts c WHERE c.date >= :start AND c.date < :end GROUP BY c.costType")
    List<Object[]> getCostDistributionInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
