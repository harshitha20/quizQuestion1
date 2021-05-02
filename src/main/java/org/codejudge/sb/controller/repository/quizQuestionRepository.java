package org.codejudge.sb.controller.repository;

import org.codejudge.sb.controller.model.Question;
import org.codejudge.sb.controller.model.QuizQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface quizQuestionRepository extends CrudRepository<QuizQuestion,Long> {
    @Query("SELECT q from Question q where q.quiz=?1")
    public List<Question> findQuestions(Long quiz_id);
}
