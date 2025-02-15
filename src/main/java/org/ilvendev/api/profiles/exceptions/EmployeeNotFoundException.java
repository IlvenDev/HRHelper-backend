package org.ilvendev.api.profiles.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer employeeId) {
        super("Employee with ID " + employeeId + " could not be found");
    }
}
