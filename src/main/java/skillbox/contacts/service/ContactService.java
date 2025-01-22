package skillbox.contacts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import skillbox.contacts.dto.ContactDto;
import skillbox.contacts.dto.DtoMapper;
import skillbox.contacts.repository.ContactRepository;
import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ContactService {
    private static final String NOT_FOUND_MSG_FORMATTED = "Could not find contact with ID = %d";

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDto getById(Long id){
        return contactRepository
                .findById(id)
                .map(DtoMapper::toDto)
                .get();
    }

    public List<ContactDto> getAll() {
        return StreamSupport
                .stream(contactRepository.findAll().spliterator(), false)
                .map(DtoMapper::toDto)
                .toList();
    }

    public void deleteById(Long id) {
        if(!contactRepository.existsById(id)) {
            throw new RuntimeException(NOT_FOUND_MSG_FORMATTED.formatted(id));
        }
        contactRepository.deleteById(id);
    }

    public ContactDto createContact(ContactDto contactDto) {
        log.info("Dto is: {}", contactDto);
        var savedContact = contactRepository.save(DtoMapper.toEntity(contactDto));
        return DtoMapper.toDto(savedContact);
    }

    public ContactDto updateContact(Long id, ContactDto contactDto) {
        if(!contactRepository.existsById(id)) {
            throw new RuntimeException(NOT_FOUND_MSG_FORMATTED.formatted(id));
        }
        var entity = DtoMapper.toEntity(contactDto);
        entity.setId(id);
        var updatedEntity = contactRepository.save(entity);
        return DtoMapper.toDto(updatedEntity);
    }

}
