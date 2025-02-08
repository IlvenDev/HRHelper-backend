package org.ilvendev.api.attendance.mappers;

import org.ilvendev.api.attendance.dto.AttendanceTimeDTO;
import org.ilvendev.database.attendance.AttendanceTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendanceTimeMapper {

    AttendanceTimeDTO mapToDTO (AttendanceTime attendanceTime);

    AttendanceTime mapToAttendanceTime (AttendanceTimeDTO attendanceTimeDTO);
}
