package org.ilvendev.payroll.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.CostType;
import org.ilvendev.enums.Department;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.payroll.domain.DepartmentCosts;
import org.ilvendev.payroll.dto.DepartmentCostsRequest;
import org.ilvendev.payroll.dto.DepartmentCostsResponse;
import org.ilvendev.payroll.mappers.DepartmentCostsMapper;
import org.ilvendev.payroll.repositories.DepartmentCostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class DepartmentCostsService {
    DepartmentCostsRepository costsRepository;
    DepartmentCostsMapper costsMapper;

    // Add costs

    // FInd by id
    // find by department
    // find by date
    // find by date range
    // find by costType

    public DepartmentCostsResponse addCosts(DepartmentCostsRequest request){
        log.debug("Creating department costs");

        DepartmentCosts createdCosts = costsRepository.save(costsMapper.toEntity(request));

        log.info("Successfully created costs with ID {}", createdCosts.getId());

        return costsMapper.toResponse(createdCosts);
    }

    public DepartmentCostsResponse getCostsById(Integer id){
        log.debug("Fetching department costs with ID {}", id);

        DepartmentCosts fetchedCosts = costsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department costs", id.toString()));

        log.info("Successfully got department costs with ID {}", id);

        return costsMapper.toResponse(fetchedCosts);
    }

    public List<DepartmentCostsResponse> getCostsForDepartment(Department department){
        log.debug("Fetching department costs for department {}", department);

        List<DepartmentCosts> fetchedCosts = costsRepository.findByDepartment(department);

        log.info("Successfully got department costs for {}", department);

        return costsMapper.toResponseList(fetchedCosts);
    }

    public List<DepartmentCostsResponse> getCostByDate(LocalDate date){
        log.debug("Fetching department costs for date {}", date);

        List<DepartmentCosts> fetchedCosts = costsRepository.findByDate(date);

        log.info("Successfully got costs for date {}", date);

        return costsMapper.toResponseList(fetchedCosts);
    }

    public List<DepartmentCostsResponse> getCostsByType(CostType type){
        log.debug("Fetching costs with type {}", type);

        List<DepartmentCosts> fetchedCosts = costsRepository.findByCostType(type);

        log.info("Successfully got costs with type {}", type);

        return costsMapper.toResponseList(fetchedCosts);
    }

    public List<DepartmentCostsResponse> getCostsByDateBetween(LocalDate startDate, LocalDate endDate){
        log.debug("Fetching costs between dates {} and {}", startDate, endDate);

        List<DepartmentCosts> fetchedCosts = costsRepository.findByDateBetween(startDate, endDate);

        log.info("Successfully got costs between dates {} and {}", startDate, endDate);

        return costsMapper.toResponseList(fetchedCosts);
    }

    public List<DepartmentCostsResponse> getAllCosts(){
        log.debug("Fetching all costs");

        List<DepartmentCosts> fetchedCosts = costsRepository.findAll();

        log.info("Successfully got all costs");

        return costsMapper.toResponseList(fetchedCosts);
    }
}
