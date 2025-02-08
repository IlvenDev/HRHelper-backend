package org.ilvendev.api.attendance.mappers;

import org.ilvendev.api.attendance.dto.LeaveDTO;
import org.ilvendev.database.attendance.Leave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    LeaveDTO mapToDTO (Leave leave);

    Leave mapToLeave (LeaveDTO leaveDTO);
}
