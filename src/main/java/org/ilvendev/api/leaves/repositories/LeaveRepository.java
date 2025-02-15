package org.ilvendev.api.leaves.repositories;

import org.ilvendev.database.attendance.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {
}
