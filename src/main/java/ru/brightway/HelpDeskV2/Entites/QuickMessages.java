package ru.brightway.HelpDeskV2.Entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("com.haulmont.jpb.LombokDataInspection")
@Entity
@Table(name = "quick")
@NoArgsConstructor
@Data
public class QuickMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

}
