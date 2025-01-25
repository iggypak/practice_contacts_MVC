package skillbox.contacts.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table("contacts")
public class Contact {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private LocalDateTime createdAt;

    public Contact(Long id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }
}
