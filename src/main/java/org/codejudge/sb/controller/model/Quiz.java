package org.codejudge.sb.controller.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="quiz")
public class Quiz {

    public Quiz(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Quiz(Long id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    public Quiz() {

    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
