package org.codejudge.sb.controller.serviceImpl;

import org.codejudge.sb.controller.model.Quiz;
import org.codejudge.sb.controller.service.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Component
public class quizServiceImpl implements quizService {

    @Autowired
    private org.codejudge.sb.controller.repository.quizRepository quizRepository;

    @Override
    public Quiz findById(Long id) {
        return quizRepository.findOne(id);
    }

    @Override
    public Quiz loadData(Quiz quiz) {
        quizRepository.save(quiz);
        return quiz;
    }

    public List<Quiz> findAllQuizWithlimit(int limit,int offset)
    {
        return quizRepository.findQuiz((Pageable) new PageRequest(offset,limit));
    }

}
