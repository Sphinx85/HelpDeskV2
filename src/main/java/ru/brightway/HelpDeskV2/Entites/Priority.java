package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "priority")
@Data
@NoArgsConstructor
public class Priority {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Message> messages = new LinkedHashSet<>();
}