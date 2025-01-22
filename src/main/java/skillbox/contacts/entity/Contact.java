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

    private String name;

    private String phone;

    private LocalDateTime createdAt;

    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }

    public Contact(Long id, String name, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }

}
