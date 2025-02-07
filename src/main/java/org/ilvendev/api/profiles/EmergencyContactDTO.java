package org.ilvendev.api.profiles;

import lombok.Data;

@Data
public class EmergencyContactDTO {
    private int id;
    private String name;
    private String lastname;
    private String relationship;
    private String email;
    private String phone;
}

