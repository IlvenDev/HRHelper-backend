package org.ilvendev.profiles.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;
import org.ilvendev.profiles.dto.EmployeeDetailResponse;
import org.ilvendev.profiles.dto.EmployeeLeaveUpdateRequest;
import org.ilvendev.profiles.dto.EmployeeRequest;
import org.ilvendev.profiles.services.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
@AllArgsConstructor
@Slf4j
@Validated
@Tag(name = "Employee management", description = "API for employee management")
public class EmployeeController {
    private final EmployeeService employeeService;

    // TODO: Rethink the request mappings and params (PathVariables might work for some of them)
    // TODO: add response in @Operation for each endpoint, to make the documentation better

    // Data manipulation

    // When creating using Insomnia, when validating pesel an object already gets created and take a valid ID from the DB. TODO: Fix
    // When using a STRING for sex instead of character instead of correct validation it throws JSON error as it cannot deserialize. Expectig Character. Might be related either to the EmployeeRequest or there could need to be more validation in the controller.
    @PostMapping("/create")
    @Operation(summary = "Create new employee")
    public ResponseEntity<EmployeeBasicResponse> createEmployee(@Valid @RequestBody @DateTimeFormat EmployeeRequest employeeData){
        log.debug("Received create request with data");

        EmployeeBasicResponse createdEmployee = employeeService.createEmployee(employeeData);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdEmployee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Integer employeeId,
                                                 @Valid @RequestBody EmployeeRequest employeeData){
        log.debug("Received update request with data");

        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeData);

        return ResponseEntity.ok(String.format("Employee with ID: %s updated", employeeId));
    }

    @PatchMapping("/{id}/leave-days")
    public ResponseEntity<?> updateLeaveDays(
            @PathVariable Integer id,
            @Valid @RequestBody EmployeeLeaveUpdateRequest request
    ) {
        employeeService.updateLeaveDays(id, request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an existing employee")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable("id") Integer employeeId){
        log.debug("Received delete request with data");

        try {
            log.info("Deleting employee with id: {}", employeeId);
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting employee", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Data acquisition

    @GetMapping("/list")
    @Operation(summary = "Get a list of employees")
    public ResponseEntity<List<EmployeeBasicResponse>> getEmployeeList(){
        log.debug("Received get list request");

        return ResponseEntity.ok(employeeService.getEmployeeList());
    }

    // Should add checking if there is such an employee
    @GetMapping("/{id}")
    @Operation(summary = "Get a detailed employee response")
    public ResponseEntity<EmployeeBasicResponse> getEmployee(@Valid @PathVariable("id") Integer employeeId){
        log.debug("Received get detail request");

        EmployeeBasicResponse fetchedEmployee = employeeService.getById(employeeId);

        return ResponseEntity.ok(fetchedEmployee);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getAllEmployeesCount() {
        long count = employeeService.countAllEmployees();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/new")
    public ResponseEntity<Long> getNewEmployeesCount(
            @RequestParam int year,
            @RequestParam int month) {
        long count = employeeService.countNewEmployeesInMonth(year, month);
        return ResponseEntity.ok(count);
    }


    // Make it so we get all the params but some may be null. SO basically a switch to check which one we have
    // (required = false) for all.
    // But fuck this for now, will get back to it
//    @GetMapping("/search")
//    @Operation(summary = "Search for an employee using parameters")
//    public ResponseEntity<List<EmployeeBasicResponse>> getEmployeeByParam(@Valid @RequestParam(required = false) String name,
//                                                                          @Valid @RequestParam String lastname,
//                                                                          @Valid @RequestParam String pesel,
//                                                                          @Valid @RequestParam String phone,
//                                                                          @Valid @RequestParam String email,
//                                                                          @Valid @RequestParam Character sex,
//                                                                          @Valid @RequestParam LocalDate dateOfBirth){
//        log.debug("Received get by param request");
//
//        // Think if this can be abused to get by all params if requesting straight to the API
//
//        // This doesn't return if only name present
//        if (name != null && lastname != null){
//            log.debug("Fetching employees by fullname");
//
//            return ResponseEntity.ok(employeeService.getEmployeeByFullName(name, lastname));
//        } else if (pesel != null) {
//            log.debug("Fetching employees by pesel");
//
//            return ResponseEntity.ok(employeeService.getEmployeeByPesel(pesel));
//        } else if (phone != null) {
//            log.debug("Fetching employees by phone");
//
//            return ResponseEntity.ok(employeeService.getEmployeeByPhone(phone));
//        } else if (email != null) {
//            log.debug("Fetching employees by email");
//
//            return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
//        } else if (sex != null) {
//            log.debug("Fetching employees by sex");
//
//            return ResponseEntity.ok(employeeService.getEmployeeBySex(sex));
//        } else if (dateOfBirth != null) {
//            log.debug("Fetching employees by date of birth");
//
//            return ResponseEntity.ok(employeeService.getEmployeeByDateOfBirth(dateOfBirth));
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
}
