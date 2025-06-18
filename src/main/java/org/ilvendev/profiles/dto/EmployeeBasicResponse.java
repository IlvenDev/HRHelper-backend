package org.ilvendev.profiles.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeBasicResponse {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String pesel;
    private String phone;
    private LocalDate dateOfBirth;
    private Character sex;
}
