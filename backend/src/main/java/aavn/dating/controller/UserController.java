package aavn.dating.controller;

import aavn.dating.entity.Question;
import aavn.dating.entity.User;
import aavn.dating.entity.User_Answer;
import aavn.dating.service.UserAnswerService;
import aavn.dating.service.UserService;
import aavn.dating.utility.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dhvy on 6/9/2017.
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAnswerService user_ansService;
    @Autowired
    private PasswordAuthentication passAuthentication;


    // meet problem with mappedBy, so can not save directly !!! Must fix here
    // save answer, if correct, return true; else, return false.
    @PostMapping()
    public ResponseEntity setAnswer( @Validated @RequestBody User user )
    {
        if ( saveAnswer(user) )
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity updateProfile( @Validated @RequestBody User user)
    {
        User userRes = userService.updateUser(user);
        if ( userRes != null){
            return new ResponseEntity<>(userRes, HttpStatus.OK);

        }

        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping( value = "/{id}")
    public ResponseEntity updateProfileImg( @PathVariable("id") Long Id, @Validated @RequestBody String img_src)
    {
        User userRes = userService.findById(Id);
        userRes.setAvaImg(img_src);
        if ( userRes != null ){
            return new ResponseEntity<>(userService.updateUser(userRes), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping( value = "/{id}")
    public ResponseEntity getProfile( @PathVariable("id") Long id) {
        User user_res = userService.findById(id);
        if ( user_res != null ){
            System.out.println("success");
            return new ResponseEntity<>( userService.findById(id), HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // get answer list from Json, set UserID, QuesID and save to database
    private boolean saveAnswer(User user){
        List<User_Answer> res = (List<User_Answer>) user.getList_ans();
        int i = 0;
        for ( User_Answer user_answer : res )
        {
            i++;
            user_answer.setUser(user);
            user_answer.setQues( new Question(i));
            if ( user_ansService.addAnswer(user_answer) == null )
                return false;
        }
        return true;
    }

}
