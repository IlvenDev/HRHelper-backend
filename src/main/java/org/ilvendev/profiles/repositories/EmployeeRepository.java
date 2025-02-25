package org.ilvendev.profiles.repositories;

import org.ilvendev.profiles.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameAndLastname(String name, String lastname);

    List<Employee> findByPesel(String pesel);

    List<Employee> findByPhone(String phone);

    List<Employee> findByEmail(String email);

    List<Employee> findByDateOfBirth(LocalDate dateOfBirth);

    List<Employee> findBySex(Character sex);

    Boolean existsByEmail(String email);
}
