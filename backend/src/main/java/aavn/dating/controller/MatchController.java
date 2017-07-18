package aavn.dating.controller;

import aavn.dating.entity.User;
import aavn.dating.service.QuestionService;
import aavn.dating.service.UserService;
import aavn.dating.utility.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by lnhien on 6/26/2017.
 */
@RestController
@RequestMapping(value = "/api/match")
public class MatchController {
    @Autowired
    private MatchingService matchService;

    @Autowired
    private UserService userService;

    @GetMapping( value = "/{id}")
    public ResponseEntity getAllQuestions(@PathVariable("id") Long id) {
        System.out.println("come here");
        User userRes = userService.findById(id);
        if (userRes != null){
            System.out.println("Not Null Here");
            LinkedHashMap<Long, Integer> test = matchService.match(userRes);
            List<Integer> mapValues = new ArrayList<>(test.values());
            System.out.println("Duong oi ");
            for ( Integer i : mapValues)
                System.out.println(i);

            return new ResponseEntity<>(test, HttpStatus.OK);
        }

        else {
            System.out.println("Null here");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

