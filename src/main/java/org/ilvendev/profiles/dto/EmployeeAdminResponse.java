package org.ilvendev.profiles.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.leaves.dto.LeaveResponse;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeAdminResponse extends EmployeeDetailResponse {
    private Set<AttendanceTimeResponse> attendanceTimes;
    private Set<LeaveResponse> leaves;
}
