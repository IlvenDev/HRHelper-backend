package org.ilvendev.profiles.dto;

import lombok.Data;

@Data
public class EmployeeBasicResponse {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
}
