package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "access")
@Data
@NoArgsConstructor
public class Access {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "access")
    private Set<User> users = new LinkedHashSet<>();
}