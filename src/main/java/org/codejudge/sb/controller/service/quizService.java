package org.codejudge.sb.controller.service;

import org.codejudge.sb.controller.model.Quiz;


public interface quizService {

    public Quiz findById(Long id);

    public Quiz loadData(Quiz quiz);

}
