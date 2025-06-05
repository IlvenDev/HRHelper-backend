package org.ilvendev.profiles.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmployeeResidenceDetailsResponse {
    private int id;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
}
