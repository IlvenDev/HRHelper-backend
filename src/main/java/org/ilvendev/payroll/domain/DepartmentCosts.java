package org.ilvendev.payroll.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department_costs")
public class DepartmentCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private CostType costType;
}
