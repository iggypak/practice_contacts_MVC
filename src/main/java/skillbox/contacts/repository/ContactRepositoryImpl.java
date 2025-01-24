package skillbox.contacts.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import skillbox.contacts.entity.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository{

    private final static String SQL_FIND_ALL = "SELECT * FROM contacts";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM contacts WHERE id = ?";
    private final static String SQL_CREATE = "INSERT INTO contacts(name, phone) VALUES(?, ?) RETURNING id";
    private final static String SQL_DELETE = "DELETE FROM contacts WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE contacts SET name = ?, phone = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Contact> contactRowMapper = (rs, rowNum) -> new Contact(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("phone")
    );

    @Override
    public Optional<Contact> findById(Long id) {
        return jdbcTemplate.query(SQL_FIND_BY_ID, contactRowMapper, id).stream().findFirst();
    }

    @Override
    public List<Contact> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, contactRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    SQL_CREATE,
                    Long.class,
                    contact.getName(),
                    contact.getPhone()
            );
            contact.setId(id);
        } else {
            jdbcTemplate.update(
                    SQL_UPDATE,
                    contact.getName(),
                    contact.getPhone(),
                    contact.getId()
            );
        }
        return contact;
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }
}
