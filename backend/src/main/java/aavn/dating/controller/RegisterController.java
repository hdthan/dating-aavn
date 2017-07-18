package aavn.dating.controller;

import aavn.dating.entity.User;
import aavn.dating.service.UserService;
import aavn.dating.utility.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dhvy on 6/9/2017.
 */
@RestController
@RequestMapping(value = "/api/register")
public class RegisterController {
    @Autowired
    private UserService userService;    

    @Autowired
    private PasswordAuthentication passAuthentication;

    @GetMapping()
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createNewUser(@Validated @RequestBody User user) {
        User user_res = null;

        // if email not duplicated, add to database
        if ( userService.findByEmail(user.getEmail()) == null ) {

//            user.setPassword(passAuthentication.hash(user.getPassword().toCharArray()));
            user_res = userService.save(user);
            return new ResponseEntity<>(user_res, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user_res, HttpStatus.NOT_FOUND);
    }
}
