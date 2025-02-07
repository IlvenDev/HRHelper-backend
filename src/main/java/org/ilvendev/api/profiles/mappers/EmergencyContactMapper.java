package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.EmergencyContactDTO;
import org.ilvendev.database.profiles.EmergencyContact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {

    EmergencyContactMapper INSTANCE = Mappers.getMapper(EmergencyContactMapper.class);

    EmergencyContactDTO emergencyContactToEmergencyContactDTO(EmergencyContact emergencyContact);

    EmergencyContact emergencyContactDTOToEmergencyContact (EmergencyContactDTO emergencyContactDTO);

}
