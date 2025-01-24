package skillbox.contacts.repository;

import skillbox.contacts.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    Optional<Contact> findById(Long id);
    List<Contact> findAll();
    void deleteById(Long aLong);
    Contact save(Contact contact);
    boolean existsById(Long aLong);
}
