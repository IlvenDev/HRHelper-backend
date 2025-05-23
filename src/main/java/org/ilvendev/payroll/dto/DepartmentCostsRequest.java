package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;

import java.math.BigDecimal;
import java.time.LocalDate;

// TODO: Add validation

@Data
public class DepartmentCostsRequest {
    private Department department;
    private LocalDate date;
    private BigDecimal amount;
    private CostType costType;
}
