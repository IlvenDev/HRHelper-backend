package org.ilvendev.api.payroll.mappers;

import org.ilvendev.api.payroll.dto.DepartmentCostsDTO;
import org.ilvendev.database.payroll.DepartmentCosts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentCostsMapper {

    DepartmentCostsDTO departmentCostsToDepartmentCostsDTO (DepartmentCosts departmentCosts);

    DepartmentCosts departmentCostsDTOToDepartmentCosts (DepartmentCostsDTO departmentCostsDTO);
}
