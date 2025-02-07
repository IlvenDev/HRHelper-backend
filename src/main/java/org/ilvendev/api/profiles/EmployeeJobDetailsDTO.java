package org.ilvendev.api.profiles;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeJobDetailsDTO {
    private int id;
    private String jobTitle;
    private String jobDescription;
    private String department;
    private String employmentType;
    private Date employmentDate;
    private Date terminationDate;
    private int directSupervisorId;
    private String workLocation;
}

