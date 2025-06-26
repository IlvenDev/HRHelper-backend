package org.ilvendev.profiles.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.ilvendev.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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
    /*private String username;
    private String password;*/
    private Role role;
    private Date dataZatrudnienia;
    private Date dataZwolnienia;
    private BigDecimal stawka;
    private String wymiarPracy;
    private String rodzajRozliczenia;
    private int staż;
    private Integer dostępneDniUrlopu;
    private Integer wykorzystaneDniUrlopu;
}
