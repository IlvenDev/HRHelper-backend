package org.ilvendev.profiles.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeLeaveUpdateRequest {
    @NotNull
    private Integer dostępneDniUrlopu;

    @NotNull
    private Integer wykorzystaneDniUrlopu;
}
