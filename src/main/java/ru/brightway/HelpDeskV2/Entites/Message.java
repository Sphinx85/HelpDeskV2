package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String description;

    @Column (insertable = false, updatable = false)
    private Integer user_id;

    @Column (insertable = false, updatable = false)
    private Integer type_id;

    @Column (insertable = false, updatable = false)
    private Integer priority_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}