package org.ilvendev.api.profiles.mappers;

import org.ilvendev.api.profiles.dto.EmergencyContactDTO;
import org.ilvendev.database.profiles.EmergencyContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {

    EmergencyContactDTO mapToDTO(EmergencyContact emergencyContact);

    EmergencyContact mapToEmergencyContact(EmergencyContactDTO emergencyContactDTO);
}
