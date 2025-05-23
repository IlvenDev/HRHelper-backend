package org.ilvendev.payroll.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;
import org.ilvendev.payroll.dto.DepartmentCostsRequest;
import org.ilvendev.payroll.dto.DepartmentCostsResponse;
import org.ilvendev.payroll.services.DepartmentCostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/costs")
@Validated
@Slf4j
@AllArgsConstructor
public class DepartmentCostsController {
    private DepartmentCostsService costsService;

    @PostMapping("/add")
    public ResponseEntity<DepartmentCostsResponse> addCosts(@Valid @RequestBody DepartmentCostsRequest request){
        log.debug("Received add costs request");

        DepartmentCostsResponse createdCosts = costsService.addCosts(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCosts)
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentCostsResponse> getCostsById(@Valid @PathVariable("id") Integer costsId){
        log.debug("Received get costs by id request with ID {}", costsId);

        DepartmentCostsResponse fetchedCosts = costsService.getCostsById(costsId);

        return ResponseEntity.ok(fetchedCosts);
    }

    @GetMapping("/get")
    public ResponseEntity<List<DepartmentCostsResponse>> getByParams(@Valid @RequestParam(required = false) Department department,
                                                                     @Valid @RequestParam(required = false) LocalDate date,
                                                                     @Valid @RequestParam(required = false) CostType costType,
                                                                     @Valid @RequestParam(required = false) LocalDate startDate,
                                                                     @Valid @RequestParam(required = false) LocalDate endDate) {
        log.debug("Received get all payments with params: " +
                "Department {}, " +
                "Date {}, " +
                "Cost type {}, " +
                "Start Date {}, " +
                "End Date {}", department, date, costType, startDate, endDate);

        List<DepartmentCostsResponse> fetchedCostsList;

        if (department != null){
            fetchedCostsList = costsService.getCostsForDepartment(department);
        } else if (date != null) {
            fetchedCostsList = costsService.getCostByDate(date);
        } else if (costType != null) {
            fetchedCostsList = costsService.getCostsByType(costType);
        } else if (startDate != null && endDate != null) {
            fetchedCostsList = costsService.getCostsByDateBetween(startDate, endDate);
        } else {
            fetchedCostsList = costsService.getAllCosts();
        }

        return ResponseEntity.ok(fetchedCostsList);
    }
}
