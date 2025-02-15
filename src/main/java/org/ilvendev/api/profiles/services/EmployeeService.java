package org.ilvendev.api.profiles.services;

import lombok.AllArgsConstructor;
import org.ilvendev.api.profiles.dto.EmergencyContactDTO;
import org.ilvendev.api.profiles.dto.EmployeeDTO;
import org.ilvendev.api.profiles.exceptions.EmployeeNotFoundException;
import org.ilvendev.api.profiles.mappers.EmployeeMapper;
import org.ilvendev.api.profiles.repositories.EmployeeRepository;
import org.ilvendev.database.profiles.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    // With the typeahead, it should be implemented with like a table first. WHere you choose for what type of data you are checking, and then the typeahead should filter on the frontend only by the selected data

    public EmployeeDTO createEmployee(EmployeeDTO employeeData){
        Employee createdEmployee = employeeRepository.save(employeeMapper.mapToEmployee(employeeData));

        return employeeMapper.mapToDTO(createdEmployee);
    }

    public List<EmployeeDTO> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer employeeId){
        return employeeMapper.mapToDTO(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId)));
    }

    public void updateEmployee(EmployeeDTO employeeData){
        if (employeeRepository.existsById(employeeData.getId())){
            employeeRepository.save(employeeMapper.mapToEmployee(employeeData));
        } else {
            throw new EmployeeNotFoundException(employeeData.getId());
        }
    }

    public void deleteEmployeeById(Integer employeeId){
        if (employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
        } else {
            throw new EmployeeNotFoundException(employeeId);
        }
    }
}
