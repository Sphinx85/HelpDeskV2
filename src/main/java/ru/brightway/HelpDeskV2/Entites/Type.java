package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность типа заявок. Классификатор.
 */

@SuppressWarnings("com.haulmont.jpb.LombokDataInspection")
@Entity
@Table(name = "type", uniqueConstraints = {
        @UniqueConstraint(name = "uc_type_id", columnNames = {"id"})
})
@Data
@NoArgsConstructor
public class Type {

    @Id
    private Integer id;
    private String description;

    @OneToMany(mappedBy = "type")
    private Set<Message> messages;
}
