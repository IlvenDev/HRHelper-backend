package org.ilvendev.attendance.mappers;

import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.dto.AttendanceTimeRequest;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AttendanceTimeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTime")
    @Mapping(target = "endTime")
    @Mapping(target = "date")
    @Mapping(target = "employee")
    AttendanceTime toEntity(AttendanceTimeRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "employee", source = "employee")
    AttendanceTimeResponse toResponse(AttendanceTime attendanceTime);

    List<AttendanceTimeResponse> toResponseList(List<AttendanceTime> attendanceTimeList);
}
