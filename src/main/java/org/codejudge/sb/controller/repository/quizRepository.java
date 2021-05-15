package org.codejudge.sb.controller.repository;

import org.codejudge.sb.controller.model.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface quizRepository extends CrudRepository<Quiz,Long> {

    @Query(value="select q from Quiz q")
    List<Quiz> findQuiz(Pageable pageable);
}
