package org.ilvendev.api.profiles.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private int id;
    private String name;
    private String lastname;
    private EmergencyContactDTO emergencyContact;
    private EmployeeJobDetailsDTO jobDetails;
    private EmployeeResidenceDetailsDTO residenceDetails;
}
