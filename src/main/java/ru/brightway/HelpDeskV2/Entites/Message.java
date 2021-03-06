package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Сущность заявки. Имеет поля:
 * id заявки
 * Описание
 * id пользователя
 * id типа
 * id приоритета
 */

@SuppressWarnings("com.haulmont.jpb.LombokDataInspection")
@Entity
@Table(name = "\"messages\"")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer support_id;

    private Boolean actual;

    private String status;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @OneToMany(mappedBy = "message")
    private List<Comments> comments;
}
