package org.ilvendev.payroll.mappers;

import org.ilvendev.payroll.dto.DepartmentCostsDTO;
import org.ilvendev.payroll.domain.DepartmentCosts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentCostsMapper {

    DepartmentCostsDTO mapToDTO (DepartmentCosts departmentCosts);

    DepartmentCosts mapToDepartmentCosts (DepartmentCostsDTO departmentCostsDTO);
}
