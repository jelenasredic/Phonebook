package com.ingsoftware.contactmanager.service.mapping;

import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.entity.ContactType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactTypeMapper {
    public ContactType mapToEntity(ContactTypeDto contactTypeDto) {
        ContactType contactType = new ContactType();
        contactType.setType(contactTypeDto.getType());
        contactType.setDescription(contactTypeDto.getDescription());
        return contactType;
    }

    public ContactTypeDto convertContactTypeToDto(ContactType contactType) {
        ContactTypeDto contactTypeDto = new ContactTypeDto();
        contactTypeDto.setType(contactType.getType());
        contactTypeDto.setDescription(contactType.getDescription());
        return contactTypeDto;
    }

    public List<ContactTypeDto> contactTypeDtoList(List<ContactType> contactTypeList) {
        List<ContactTypeDto> contactTypeDtoList = new ArrayList<>();
        for (var contactType : contactTypeList) {
            var contactTypeDto = convertContactTypeToDto(contactType);
            contactTypeDtoList.add(contactTypeDto);
        }
        return contactTypeDtoList;
    }
}
