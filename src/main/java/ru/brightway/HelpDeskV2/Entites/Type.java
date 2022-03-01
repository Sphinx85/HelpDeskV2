package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
public class Type {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "type")
    private Set<Message> messages = new LinkedHashSet<>();
}