package org.ilvendev.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DepartmentCostsResponse {
    private Integer id;
    private Department department;
    private LocalDate date;
    private BigDecimal amount;
    private CostType costType;
}
