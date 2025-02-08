package org.ilvendev.database.payroll;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "department_costs")
public class DepartmentCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Temporal(TemporalType.DATE)
    private Date date;
    private BigDecimal amount;
    private CostType costType;
}
