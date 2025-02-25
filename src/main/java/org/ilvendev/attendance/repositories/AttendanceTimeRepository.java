package org.ilvendev.attendance.repositories;

import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.profiles.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceTimeRepository extends JpaRepository<AttendanceTime, Integer> {

    List<AttendanceTime> findByEmployee(Employee employee);

    List<AttendanceTime> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<AttendanceTime> findByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);

}
