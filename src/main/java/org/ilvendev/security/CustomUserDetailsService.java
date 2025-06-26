package org.ilvendev.security;

import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}