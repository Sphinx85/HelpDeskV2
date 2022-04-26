package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("com.haulmont.jpb.LombokDataInspection")
@Entity
@Table(name = "comment")
@NoArgsConstructor
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
}
