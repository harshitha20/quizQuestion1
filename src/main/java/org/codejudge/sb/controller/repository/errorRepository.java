package org.codejudge.sb.controller.repository;

import org.codejudge.sb.controller.model.Error;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface errorRepository extends CrudRepository<Error,Long> {
}
