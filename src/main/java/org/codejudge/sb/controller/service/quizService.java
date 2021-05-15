package org.codejudge.sb.controller.service;

import org.codejudge.sb.controller.model.Quiz;


public interface quizService {

    Quiz findById(Long id);

    Quiz loadData(Quiz quiz);

}
