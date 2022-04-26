package ru.brightway.HelpDeskV2.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "keywords", uniqueConstraints = {
        @UniqueConstraint(name = "uc_keywords_keyword", columnNames = {"keyword"})
})
@Data
@NoArgsConstructor

public class KeyWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String keyword;
    private Integer priorityId;
    private Integer typeId;


    public KeyWords(String keyword, Integer priorityId, Integer typeId) {
        this.keyword = keyword;
        this.priorityId = priorityId;
        this.typeId = typeId;
    }
}
