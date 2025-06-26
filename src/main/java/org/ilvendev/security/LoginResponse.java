package org.ilvendev.security;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String role;

    public LoginResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
