package org.ilvendev.api.profiles.dto;

import lombok.*;
import org.ilvendev.api.attendance.dto.AttendanceTimeDTO;
import org.ilvendev.api.leaves.dto.LeaveDTO;
import org.ilvendev.api.payroll.dto.EmployeePaymentDetailsDTO;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String lastname;
    private String pesel;
    private EmergencyContactDTO emergencyContact;
    private EmployeeJobDetailsDTO jobDetails;
    private EmployeeResidenceDetailsDTO residenceDetails;
    private Date dateOfBirth;
    private Character sex;
    private List<AttendanceTimeDTO> attendanceTimes;
    private List<LeaveDTO> leaves;
    private EmployeePaymentDetailsDTO employeePaymentDetails;
}
