package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.controller.model.Question;
import org.codejudge.sb.controller.model.Quiz;
import org.codejudge.sb.controller.model.QuizQuestion;
import org.codejudge.sb.controller.serviceImpl.errorServiceImpl;
import org.codejudge.sb.controller.serviceImpl.questionServiceImpl;
import org.codejudge.sb.controller.serviceImpl.quizQuestionServiceImpl;
import org.json.JSONObject;
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

    @Autowired
    private quizQuestionServiceImpl quizQuestionServiceImpl;

    @ApiOperation("This is the hello world api")
    @GetMapping("/")
    public String hello() {
        return "Hello World!!";
    }

    @GetMapping("/api/quiz/{quiz_id}")
    public ResponseEntity findById(@PathVariable Long quiz_id) {
        Quiz quiz = quizService.findById(quiz_id);
        if (quiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmptyJsonBody());
        }
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

    @RequestMapping(path = "/api/quiz/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addQuiz(@RequestBody String quiz) {
        JSONObject json = new JSONObject(quiz);
        try {
            if (!json.has("id") && json.has("name") && json.has("description")) {
                String name = json.get("name").toString();
                String description = json.get("description").toString();
                Quiz newQuiz = new Quiz();
                newQuiz.setName(name);
                newQuiz.setDescription(description);
                quizService.loadData(newQuiz);
                return ResponseEntity.status(HttpStatus.CREATED).body(newQuiz);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(6)));
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(2)));
        }

    }

    @GetMapping("/api/questions/{question_id}")
    public ResponseEntity findQuestionById(@PathVariable Long question_id) {
        Question question = questionServiceImpl.findById(question_id);
        if (question == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmptyJsonBody());
        }
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @GetMapping("/api/*/")
    public ResponseEntity entityIdNotProvided() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(1)));
    }

    @RequestMapping(path = "/api/questions/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addQuestion(@RequestBody String question) {
        JSONObject json = new JSONObject(question);
        try {
            if(!json.has("id") && json.has("name") && json.has("options")
            && json.has("correct_option") && json.has("quiz") && json.has("points")) {
                Question newQuestion = new Question();
                newQuestion.setName(json.get("name").toString());
                newQuestion.setOptions(json.get("options").toString());
                newQuestion.setCorrect_option(Integer.valueOf(json.get("correct_option").toString()));
                newQuestion.setQuiz(Long.valueOf(json.get("quiz").toString()));
                newQuestion.setPoints(Integer.valueOf(json.get("points").toString()));
                if (newQuestion != null) {
                    String options = newQuestion.getOptions();
                    List optionsList = Arrays.asList(options.split(","));
                    if (optionsList.size() > 0 && (newQuestion.getCorrect_option() > optionsList.size() || newQuestion.getCorrect_option() <= 0)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(4)));
                    }
                    Long quiz_id = newQuestion.getQuiz();
                    if (quizService.findById(quiz_id) == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(5)));
                    }
                }
                questionServiceImpl.loadData(newQuestion);
                return ResponseEntity.status(HttpStatus.CREATED).body(newQuestion);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(6)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(3)));
        }
    }

    @GetMapping("/api/quiz-questions/{quiz_id}")
    public ResponseEntity questionByQuizId(@PathVariable Long quiz_id) {
        if(quiz_id==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.findById(Long.valueOf(1)));
        }
        QuizQuestion quiz = quizQuestionServiceImpl.findQuizQuestionById(quiz_id);
        if (quiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmptyJsonBody());
        }
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

}
