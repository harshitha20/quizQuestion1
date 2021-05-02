package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.controller.model.Error;
import org.codejudge.sb.controller.model.Question;
import org.codejudge.sb.controller.model.Quiz;
import org.codejudge.sb.controller.serviceImpl.errorServiceImpl;
import org.codejudge.sb.controller.serviceImpl.questionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
public class AppController {

    @Autowired
    private org.codejudge.sb.controller.service.quizService quizService;

    @Autowired
    private errorServiceImpl errorService;

    @Autowired
    private questionServiceImpl questionServiceImpl;

    @ApiOperation("This is the hello world api")
    @GetMapping("/")
    public String hello() {
        return "Hello World!!";
    }

    @GetMapping("/api/quiz/{quiz_id}")
    public ResponseEntity findById(@PathVariable Long quiz_id)
    {
        Quiz quiz = quizService.findById(quiz_id);
        if(quiz==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmptyJsonBody());
        }
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

    /*@GetMapping("/api/quiz/")
    public ResponseEntity<Error> findError()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(1)));
    }
*/
    @RequestMapping(path = "/api/quiz/",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addQuiz(@RequestBody Quiz quiz)
    {
        Quiz newQuiz = quiz;
        try {
            quizService.loadData(quiz);
            return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(2)));
        }
    }

    @GetMapping("/api/questions/{question_id}")
    public ResponseEntity findQuestionById(@PathVariable Long question_id)
    {
        Question question = questionServiceImpl.findById(question_id);
        if(question==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmptyJsonBody());
        }
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @GetMapping("/api/*/")
    public ResponseEntity entityIdNotProvided()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(1)));
    }

    @RequestMapping(path = "/api/questions/",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addQuestion(@RequestBody Question question)
    {
        Question newQuestion = question;
        if(newQuestion!=null) {
            String options = newQuestion.getOptions();
            List optionsList = Arrays.asList(options.split(","));
            if(optionsList.size()>0 && (newQuestion.getCorrect_option()>optionsList.size() || newQuestion.getCorrect_option()<=0))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(4)));
            }
            Long quiz_id = newQuestion.getQuiz();
            if(quizService.findById(quiz_id)==null)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(5)));
            }
        }
        try {
            questionServiceImpl.loadData(newQuestion);
            return ResponseEntity.status(HttpStatus.CREATED).body(question);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(3)));
        }
    }

}
