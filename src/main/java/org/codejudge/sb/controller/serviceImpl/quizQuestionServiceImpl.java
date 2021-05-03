package org.codejudge.sb.controller.serviceImpl;

import org.codejudge.sb.controller.model.Question;
import org.codejudge.sb.controller.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class quizQuestionServiceImpl{

    List<Question> question = null;

    @Autowired
    private org.codejudge.sb.controller.repository.quizQuestionRepository quizQuestionRepository;

    public List<Question> findQuestions(Long quiz_id)
    {
        question = quizQuestionRepository.findQuestions(quiz_id);
        return question;
    }

    public QuizQuestion findQuizQuestionById(Long quiz_id)
    {
        QuizQuestion quizQuestion = quizQuestionRepository.findOne(quiz_id);
        if(quizQuestion!=null)
            findQuestions(quiz_id);
        if(question!=null){
            quizQuestion.setQuestions(question);
            return quizQuestion;
        }
        return null;
    }
}
