package org.ilvendev.leaves.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;

import java.time.LocalDate;

@Data
public class LeaveResponse {
    private int id;
    private LocalDate dataStart;
    private LocalDate dataKoniec;
    private LeaveType rodzaj;
    private LeaveStatus status;
    private LocalDate złożono;
    private EmployeeBasicResponse employee;
}
