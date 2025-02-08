package org.ilvendev.api.payroll.dto;

import lombok.Data;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DepartmentCostsDTO {
    private int id;
    private Department department;
    private Date date;
    private BigDecimal amount;
    private CostType costType;
}
