package org.codejudge.sb.controller.model;

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
public class QuizQuestion {

    @Id
    private Long id;

    private String name;

    private String description;

    @Transient
    private List<Question> questionList;

    public QuizQuestion(String name, String description, List<Question> questionList) {
        this.name = name;
        this.description = description;
        this.questionList = questionList;
    }

    public QuizQuestion() {
    }
}
