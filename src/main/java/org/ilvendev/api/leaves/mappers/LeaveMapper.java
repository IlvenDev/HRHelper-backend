package org.ilvendev.api.leaves.mappers;

import org.ilvendev.api.leaves.dto.LeaveDTO;
import org.ilvendev.database.attendance.Leave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    LeaveDTO mapToDTO (Leave leave);

    Leave mapToLeave (LeaveDTO leaveDTO);
}
