package org.ilvendev.leaves.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.enums.ValueOfEnum;

import java.time.LocalDate;

@Data
public class LeaveRequest {
    private LocalDate dataStart;
    private LocalDate dataKoniec;
    private LeaveType rodzaj;
    private LocalDate złożono;
    private Integer employeeId;
}
