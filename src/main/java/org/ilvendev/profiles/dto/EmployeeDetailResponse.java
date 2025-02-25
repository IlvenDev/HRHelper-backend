package org.ilvendev.profiles.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDetailResponse extends EmployeeBasicResponse {
    private String pesel;
    private String phone;
    private LocalDate dateOfBirth;
    private Character sex;
    private EmergencyContactResponse emergencyContact;
    private EmployeeJobDetailsResponse jobDetails;
    private EmployeeResidenceDetailsResponse residenceDetails;
    private EmployeePaymentDetailsResponse employeePaymentDetails;
}
