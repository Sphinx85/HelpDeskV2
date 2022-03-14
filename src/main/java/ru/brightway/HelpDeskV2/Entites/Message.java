package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"messages\"")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private Priority priority;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "type_id")
//    private Set<Type> types;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "priority_id")
//    private Set<Priority> priorities;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column (name = "description")
//    private String description;
//
//    @Column (name = "user_id", insertable = false, updatable = false)
//    private Integer user_id;
//
//    @Column (name = "type_id",insertable = false, updatable = false)
//    private Integer type_id;
//
//    @Column (name = "priority_id",insertable = false, updatable = false)
//    private Integer priority_id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "priority_id")
//    private Priority priority;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_id")
//    private Type type;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
}