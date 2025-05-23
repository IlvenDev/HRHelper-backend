package org.ilvendev.payroll.mappers;

import org.ilvendev.payroll.domain.DepartmentCosts;
import org.ilvendev.payroll.dto.DepartmentCostsRequest;
import org.ilvendev.payroll.dto.DepartmentCostsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DepartmentCostsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department")
    @Mapping(target = "date")
    @Mapping(target = "amount")
    @Mapping(target = "costType")
    DepartmentCosts toEntity(DepartmentCostsRequest request);

    DepartmentCostsResponse toResponse(DepartmentCosts departmentCosts);

    List<DepartmentCostsResponse> toResponseList(List<DepartmentCosts> costsList);
}
