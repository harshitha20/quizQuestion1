package org.codejudge.sb.controller.serviceImpl;

import org.codejudge.sb.controller.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class errorServiceImpl {

    @Autowired
    private org.codejudge.sb.controller.repository.errorRepository errorRepository;

    public Error findAll() {
        return errorRepository.findAll().iterator().next();
    }

    public Error findById(Long id){
        return errorRepository.findOne(id);
    }
}
