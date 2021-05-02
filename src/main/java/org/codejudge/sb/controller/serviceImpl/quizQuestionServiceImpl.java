package org.codejudge.sb.controller.serviceImpl;

import org.codejudge.sb.controller.model.Question;
import org.codejudge.sb.controller.model.Quiz;
import org.codejudge.sb.controller.service.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class questionServiceImpl {

    @Autowired
    private org.codejudge.sb.controller.repository.questionRepository questionRepository;

    public Question findById(Long id) {
        return questionRepository.findOne(id);
    }

    public Question loadData(Question question) {
        questionRepository.save(question);
        return question;
    }
}
