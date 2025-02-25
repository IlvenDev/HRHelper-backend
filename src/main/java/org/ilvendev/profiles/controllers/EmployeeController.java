package org.ilvendev.profiles.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;
import org.ilvendev.profiles.dto.EmployeeDetailResponse;
import org.ilvendev.profiles.dto.EmployeeRequest;
import org.ilvendev.profiles.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    // TODO: Rethink the request mappings and params (PathVariables might work for some of them)

    // Data manipulation

    @PostMapping("/create")
    public ResponseEntity<EmployeeDetailResponse> createEmployee(@RequestBody EmployeeRequest employeeData){
        log.debug("Received create request with data");

        return ResponseEntity.ok(employeeService.createEmployee(employeeData));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestParam Integer employeeId,
                                                 @RequestBody EmployeeRequest employeeData){
        log.debug("Received update request with data");

        employeeService.updateEmployee(employeeId, employeeData);

        return ResponseEntity.ok(String.format("Employee with ID: %s updated", employeeId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam Integer employeeId){
        log.debug("Received delete request with data");

        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok(String.format("Employee with ID: %s deleted", employeeId));
    }

    // Data acquisition

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeBasicResponse>> getEmployeeList(){
        log.debug("Received get list request");

        return ResponseEntity.ok(employeeService.getEmployeeList());
    }

    @GetMapping
    public ResponseEntity<EmployeeDetailResponse> getEmployeeDetail(@PathVariable("id") Integer employeeId){
        log.debug("Received get detail request");

        EmployeeDetailResponse fetchedEmployee = employeeService.getEmployeeDetail(employeeId);

        return ResponseEntity.ok(fetchedEmployee);
    }


    // Make it so we get all the params but some may be null. SO basically a switch to check which one we have
    // Might need to make the params Optional<>
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeBasicResponse>> getEmployeeByParam(@RequestParam String name,
                                                                          @RequestParam String lastname,
                                                                          @RequestParam String pesel,
                                                                          @RequestParam String phone,
                                                                          @RequestParam String email,
                                                                          @RequestParam Character sex,
                                                                          @RequestParam LocalDate dateOfBirth){
        log.debug("Received get by param request");

        // Think if this can be abused to get by all params if requesting straight to the API

        // This doesn't return if only name present
        if (name != null && lastname != null){
            log.debug("Fetching employees by fullname");

            return ResponseEntity.ok(employeeService.getEmployeeByFullName(name, lastname));
        } else if (pesel != null) {
            log.debug("Fetching employees by pesel");

            return ResponseEntity.ok(employeeService.getEmployeeByPesel(pesel));
        } else if (phone != null) {
            log.debug("Fetching employees by phone");

            return ResponseEntity.ok(employeeService.getEmployeeByPhone(phone));
        } else if (email != null) {
            log.debug("Fetching employees by email");

            return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
        } else if (sex != null) {
            log.debug("Fetching employees by sex");

            return ResponseEntity.ok(employeeService.getEmployeeBySex(sex));
        } else if (dateOfBirth != null) {
            log.debug("Fetching employees by date of birth");

            return ResponseEntity.ok(employeeService.getEmployeeByDateOfBirth(dateOfBirth));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
