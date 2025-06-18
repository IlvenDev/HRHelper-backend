package org.ilvendev.leaves.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.enums.ValueOfEnum;

import java.time.LocalDate;

@Data
public class LeaveRequest {
    @FutureOrPresent(message = "Leave date must be today or in the future")
    @NotNull(message = "Leave date cannot be null")
    private LocalDate date;
    @NotNull(message = "Leave type cannot be null")
//    @ValueOfEnum(enumClass = LeaveType.class)
    private LeaveType leaveType;
    @NotNull(message = "Employee id cannot be null")
    private Integer employeeId;
}
