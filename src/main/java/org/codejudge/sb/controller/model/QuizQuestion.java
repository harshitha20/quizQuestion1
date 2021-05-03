package org.codejudge.sb.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="quiz")
@Getter
@Setter
@JsonIgnoreProperties("id")
public class QuizQuestion {

    @Id
    private Long id;

    private String name;

    private String description;

    @Transient
    private List<Question> questions;

    public QuizQuestion(String name, String description, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;
    }

    public QuizQuestion() {
    }
}
