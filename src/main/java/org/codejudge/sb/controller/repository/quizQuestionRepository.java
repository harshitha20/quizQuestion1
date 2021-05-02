package org.codejudge.sb.controller.repository;

import org.codejudge.sb.controller.model.Question;
import org.codejudge.sb.controller.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface questionRepository extends CrudRepository<Question,Long> {
}
