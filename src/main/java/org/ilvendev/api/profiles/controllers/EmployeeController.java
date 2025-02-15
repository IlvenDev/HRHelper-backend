package org.ilvendev.api.profiles.controllers;

import lombok.AllArgsConstructor;
import org.ilvendev.api.profiles.dto.EmployeeDTO;
import org.ilvendev.api.profiles.exceptions.EmployeeNotFoundException;
import org.ilvendev.api.profiles.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profiles")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create_employee")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeData){
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeData);

        return ResponseEntity.ok("Created employee with ID: " + employeeData.getId());
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employeeList = employeeService.getAllEmployees();

        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/get_employee")
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestParam("employeeId") Integer employeeId){
        EmployeeDTO fetchedEmployee;

        try {
            fetchedEmployee = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok().body(fetchedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO employeeData){
        try {
            employeeService.updateEmployee(employeeData);
            return ResponseEntity.ok().body("Employee with ID " + employeeData.getId() + " updated successfully");
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") Integer employeeId){
        try {
            employeeService.deleteEmployeeById(employeeId);
            return ResponseEntity.ok().body("Employee with id " + employeeId + " successfully deleted");
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
