package org.ilvendev.leaves.repositories;

import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.profiles.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByEmployee(Employee employee);

    List<Leave> findByDate(LocalDate date);

    List<Leave> findByLeaveType(LeaveType leaveType);

    List<Leave> findByLeaveStatus(LeaveStatus leaveStatus);

    @Query("SELECT COUNT(l) FROM Leave l WHERE l.date >= :start AND l.date < :end")
    long countLeavesInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // Rozkład typów urlopów w danym miesiącu (typ urlopu i liczba)
    @Query("SELECT l.leaveType, COUNT(l) FROM Leave l WHERE l.date >= :start AND l.date < :end GROUP BY l.leaveType")
    List<Object[]> countLeavesByTypeInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
