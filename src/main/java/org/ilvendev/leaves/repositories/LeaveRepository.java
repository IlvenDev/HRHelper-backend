package org.ilvendev.leaves.repositories;

import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.profiles.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByEmployee(Employee employee);

    List<Leave> findByDate(LocalDate date);

    List<Leave> findByLeaveType(LeaveType leaveType);

    List<Leave> findByLeaveStatus(LeaveStatus leaveStatus);
}
