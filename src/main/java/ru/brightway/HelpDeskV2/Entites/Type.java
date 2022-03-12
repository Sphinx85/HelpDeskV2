package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.property.access.spi.SetterMethodImpl;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
public class Type {

    @Id
    private Integer id;
    private String description;

    @OneToMany(mappedBy = "type")
    private Set<Message> messages;

//    @ManyToMany(mappedBy = "types")
//    private Set<Message> messages;
//    @Id
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "description")
//    private String description;
//
//    @OneToMany(mappedBy = "type")
//    private Set<Message> messages = new LinkedHashSet<>();
}