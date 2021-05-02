package org.codejudge.sb.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties("id")
@Table(name = "error")
public class Error {

    public Error(String status,String explanation)
    {
        this.status = status;
        this.explanation = explanation;
    }
    /*public Error(Long id,String status,String explanation)
    {
        this.id = id;
        this.status = status;
        this.explanation = explanation;
    }*/
    public Error(){

    }

    @Id
    private Long id;

    private String status;

    private String explanation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
