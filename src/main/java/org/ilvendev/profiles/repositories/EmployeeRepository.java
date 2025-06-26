package org.ilvendev.profiles.repositories;

import org.ilvendev.profiles.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameAndLastname(String name, String lastname);

    List<Employee> findByPesel(String pesel);

    List<Employee> findByPhone(String phone);

    List<Employee> findByEmail(String email);

    List<Employee> findByDateOfBirth(LocalDate dateOfBirth);

    List<Employee> findBySex(Character sex);

    Boolean existsByEmail(String email);

    Optional<Employee> findByUsername(String username);

    long countBy();

    @Query("SELECT COUNT(e) FROM Employee e JOIN e.jobDetails j WHERE j.employmentDate >= :start AND j.employmentDate < :end")
    long countNewEmployeesInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

}