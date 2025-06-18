package org.ilvendev.profiles.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ilvendev.payroll.dto.EmployeePaymentDetailsResponseDetail;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDetailResponse extends EmployeeBasicResponse {
    private EmergencyContactResponse emergencyContact;
    private EmployeeJobDetailsResponse jobDetails;
    private EmployeeResidenceDetailsResponse residenceDetails;
}
