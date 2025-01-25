package skillbox.contacts.dto;

import skillbox.contacts.entity.Contact;

public class DtoMapper {
    public static Contact toEntity(ContactDto dto) {
        var entity = new Contact();
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        entity.setId(dto.id());
        return entity;
    }

    public static ContactDto toDto(Contact entity) {
        var dto = new ContactDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone()
        );
        return dto;
    }
}
