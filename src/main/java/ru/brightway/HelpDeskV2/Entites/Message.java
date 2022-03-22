package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import javax.persistence.*;

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

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer support_id;

    private boolean actual;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    public Message() {
        new StatusConstructor();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Data
    private class StatusConstructor{
        UserService userService;
        private StringBuilder status_complete;

        public StatusConstructor() {
            if (support_id == 0)
                status = status_complete.append("Вашу заявку еще не приняли в работу").toString();
            status = status_complete.append("Вашей заявкой занимается: ")
                    .append(userService.findById(support_id).get().getFirst_name())
                    .append(" ")
                    .append(userService.findById(support_id).get().getLast_name())
                    .append(".").toString();
        }
    }
}