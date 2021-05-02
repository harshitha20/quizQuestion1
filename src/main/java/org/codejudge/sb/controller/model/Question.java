package org.codejudge.sb.controller.model;

import javax.persistence.*;

@Entity
@Table(name="question")
public class Question {

    public Question()
    {

    }

    public Question(Long id,String name, String options, Integer correct_option,
                    Long quiz, Integer points)
    {
        this.id = id;
        this.name = name;
        this.options = options;
        this.correct_option = correct_option;
        this.quiz = quiz;
        this.points = points;
    }

    public Question(String name, String options, Integer correct_option,
                    Long quiz, Integer points)
    {
        this.name = name;
        this.options = options;
        this.correct_option = correct_option;
        this.quiz = quiz;
        this.points = points;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String options;

    private Integer correct_option;

    private Long quiz;

    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(Integer correct_option) {
        this.correct_option = correct_option;
    }

    public Long getQuiz() {
        return quiz;
    }

    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
