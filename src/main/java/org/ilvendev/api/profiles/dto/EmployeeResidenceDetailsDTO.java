package org.ilvendev.api.profiles.dto;

import lombok.Data;

@Data
public class EmployeeResidenceDetailsDTO {
    private int id;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
}

