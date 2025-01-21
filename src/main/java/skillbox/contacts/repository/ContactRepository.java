package skillbox.contacts.repository;

import org.springframework.data.repository.CrudRepository;
import skillbox.contacts.entity.Contact;

import java.util.Optional;

public interface ContactRepository extends CrudRepository <Contact, Long> {

    Optional<Contact> findById(Long id);
    Iterable<Contact> findAll();
    void deleteById(Long aLong);
    Contact save(Contact contact);
    boolean existsById(Long aLong);
}
