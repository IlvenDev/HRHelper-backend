package org.ilvendev.profiles.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.exceptions.resource_exceptions.DuplicateResourceException;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.profiles.domain.EmergencyContact;
import org.ilvendev.profiles.domain.EmployeeJobDetails;
import org.ilvendev.profiles.domain.EmployeeResidenceDetails;
import org.ilvendev.profiles.dto.EmployeeAdminResponse;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;
import org.ilvendev.profiles.dto.EmployeeDetailResponse;
import org.ilvendev.profiles.dto.EmployeeRequest;
import org.ilvendev.profiles.mappers.EmployeeMapper;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.ilvendev.profiles.domain.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    // With the typeahead, it should be implemented with like a table first. WHere you choose for what type of data you are checking, and then the typeahead should filter on the frontend only by the selected data

    // Let's go with typeahead that queries the backend. Will use debouncing on frontend and should be good
    // TODO: Add data validation

    @Transactional
    public EmployeeDetailResponse createEmployee(EmployeeRequest employeeData) throws DuplicateResourceException {
        log.debug("Creating employee with data: {}", employeeData);

        if (employeeRepository.existsByEmail(employeeData.getEmail())) {
            throw new DuplicateResourceException("Employee", employeeData.getEmail());
        }

        // Create base employee
        Employee employee = employeeMapper.toEntity(employeeData);

        // Map and link job details
        EmployeeJobDetails jobDetails = employeeMapper.toJobDetailsEntity(employeeData.getJobDetails());
        jobDetails.setEmployee(employee);  // 🔥 Critical line
        employee.setJobDetails(jobDetails);

        // Map and link residence
        EmployeeResidenceDetails residence = employeeMapper.toResidenceDetailsEntity(employeeData.getResidenceDetails());
        residence.setEmployee(employee);  // 🔥 Critical line
        employee.setResidenceDetails(residence);

        // Map and link emergency contact
        EmergencyContact emergencyContact = employeeMapper.toEmergencyContactEntity(employeeData.getEmergencyContact());
        emergencyContact.setEmployee(employee);  // 🔥 Critical line
        employee.setEmergencyContact(emergencyContact);

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Created employee with ID: {}", savedEmployee.getId());
        return employeeMapper.toDetailResponse(savedEmployee);
    }


    @Transactional
    public Employee updateEmployee(Integer employeeId, EmployeeRequest employeeData) {
        log.debug("Updating employee with ID: {}", employeeId);

        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    log.error("Failed updating employee - ID {} not found", employeeId);
                    return new ResourceNotFoundException("Employee", employeeId.toString());
                });

        // Update employee fields
        existingEmployee.setName(employeeData.getName());
        existingEmployee.setLastname(employeeData.getLastname());
        existingEmployee.setPesel(employeeData.getPesel());
        existingEmployee.setPhone(employeeData.getPhone());
        existingEmployee.setEmail(employeeData.getEmail());
        existingEmployee.setDateOfBirth(employeeData.getDateOfBirth());
        existingEmployee.setSex(employeeData.getSex());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        log.info("Employee with ID: {} updated successfully", employeeId);

        return updatedEmployee;
    }

    @Transactional
    public void deleteEmployee(Integer employeeId) throws ResourceNotFoundException{
        log.debug("Deleting employee with ID: {}", employeeId);
        if (employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
            log.info("Employee deleted successfully");
        } else {
            log.error("Failed deleting employee");
            throw new ResourceNotFoundException("Employee", employeeId.toString());
        }
    }

    @Transactional
    public Employee findById(Integer employeeId){
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId.toString()));
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeList(){
        log.debug("Fetching employee list");
        return employeeMapper.toBasicResponseList(employeeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EmployeeBasicResponse getEmployeeBasic(Integer employeeId) throws ResourceNotFoundException{
        log.debug("Fetching basic employee data for ID: {}", employeeId);
        Employee fetchedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId.toString()));

        log.info("Basic employee data fetched successfully");
        return employeeMapper.toBasicResponse(fetchedEmployee);
    }

    @Transactional(readOnly = true)
    public EmployeeDetailResponse getEmployeeDetail(Integer employeeId) throws ResourceNotFoundException{
        log.debug("Fetching detail employee data for ID: {}", employeeId);
        Employee fetchedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId.toString()));

        log.info("Detail employee data fetched successfully");
        return employeeMapper.toDetailResponse(fetchedEmployee);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public EmployeeAdminResponse getEmployeeAdmin(Integer employeeId) throws ResourceNotFoundException{
        log.debug("Fetching admin employee data for ID: {}", employeeId);
        Employee fetchedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId.toString()));

        log.info("Admin employee data fetched successfully");
        return employeeMapper.toAdminResponse(fetchedEmployee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeByFullName(String name, String lastname){
        log.debug("Fetching employee by name {} and lastname {}", name, lastname);
        List<Employee> fetchedEmployees = employeeRepository.findByNameAndLastname(name, lastname);

        return fetchedEmployees
                .stream()
                .map(employeeMapper::toBasicResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeByPesel(String pesel){
        log.debug("Fetching employee by pesel {}", pesel);
        List<Employee> fetchedEmployees = employeeRepository.findByPesel(pesel);

        return fetchedEmployees
                .stream()
                .map(employeeMapper::toBasicResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeByPhone(String phone){
        log.debug("Fetching employee by phone {}", phone);
        List<Employee> fetchedEmployees = employeeRepository.findByPhone(phone);

        return fetchedEmployees
                .stream()
                .map(employeeMapper::toBasicResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeByEmail(String email){
        log.debug("Fetching employee by email {}", email);
        List<Employee> fetchedEmployees = employeeRepository.findByEmail(email);

        return fetchedEmployees
                .stream()
                .map(employeeMapper::toBasicResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeBySex(Character sex){
        log.debug("Fetching employee by sex {}", sex);
        List<Employee> fetchedEmployees = employeeRepository.findBySex(sex);

        return fetchedEmployees
                .stream()
                .map(employeeMapper::toBasicResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicResponse> getEmployeeByDateOfBirth(LocalDate dateOfBirth){
        log.debug("Fetching employee by date of birth {}", dateOfBirth);
        List<Employee> fetchedEmployees = employeeRepository.findByDateOfBirth(dateOfBirth);

        return fetchedEmployees
                .stream()
                .map(employeeMapper::toBasicResponse)
                .collect(Collectors.toList());
    }

    public long countAllEmployees() {
        return employeeRepository.countBy();
    }

    public long countNewEmployeesInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);
        return employeeRepository.countNewEmployeesInMonth(start, end);
    }
}