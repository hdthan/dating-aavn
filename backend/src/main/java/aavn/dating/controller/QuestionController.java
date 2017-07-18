package aavn.dating.controller;

import aavn.dating.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lnhien on 6/20/2017.
 */
@RestController
@RequestMapping(value = "/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public ResponseEntity getAllQuestions() {
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }
}
