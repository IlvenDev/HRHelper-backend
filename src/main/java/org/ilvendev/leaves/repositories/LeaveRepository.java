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

    @Query("SELECT l FROM Leave l WHERE l.dataStart <= :endDate AND l.dataKoniec >= :startDate")
    List<Leave> findByTimeframe(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);

    List<Leave> findByRodzaj(LeaveType rodzaj);

    List<Leave> findByStatus(LeaveStatus status);

    List<Leave> findByZłożono(LocalDate złożono);

    @Query("SELECT COUNT(l) FROM Leave l WHERE l.dataStart >= :start AND l.dataKoniec < :end")
    long countLeavesInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // Rozkład typów urlopów w danym miesiącu (typ urlopu i liczba)
    @Query("SELECT l.rodzaj, COUNT(l) FROM Leave l WHERE l.dataStart >= :start AND l.dataKoniec < :end GROUP BY l.rodzaj")
    List<Object[]> countLeavesByTypeInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);

    List<Leave> findByEmployeeIdAndRodzajInAndStatus(
            Integer employeeId, List<LeaveType> rodzaje, LeaveStatus status);

    @Query("""
      SELECT l FROM Leave l
       WHERE l.status = :status
         AND l.dataStart <= :periodEnd
         AND l.dataKoniec >= :periodStart
    """)
    List<Leave> findApprovedLeavesInPeriod(
            @Param("status") LeaveStatus status,
            @Param("periodStart") LocalDate periodStart,
            @Param("periodEnd")   LocalDate periodEnd
    );

    @Query("""
    SELECT l FROM Leave l
     WHERE l.status = :status
       AND l.employee = :employee
       AND l.dataStart <= :periodEnd
       AND l.dataKoniec >= :periodStart
    """)
    List<Leave> findApprovedLeavesInPeriodForEmployee(
            @Param("status") LeaveStatus status,
            @Param("employee") Employee employee,
            @Param("periodStart") LocalDate periodStart,
            @Param("periodEnd") LocalDate periodEnd
    );

}
