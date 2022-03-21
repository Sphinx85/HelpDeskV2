package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Сущность приоритета заявки
 * id
 * Описание
 */
@SuppressWarnings("ALL")
@Entity
@Table(name = "priority")
@Data
@NoArgsConstructor
public class Priority {

    @Id
    private Integer id;
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Message> messages;
}