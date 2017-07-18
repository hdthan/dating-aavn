package aavn.dating.controller;

import aavn.dating.entity.User;
import aavn.dating.service.UserService;
import aavn.dating.utility.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dhvy on 6/12/2017.
 */
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordAuthentication passAuthenticate;

    @PostMapping()
    public ResponseEntity confirmUser(@Validated @RequestBody User user) {

        User user_res = userService.findUser(user);
        if ( user_res == null ) {
            System.out.println("");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(user_res, HttpStatus.CREATED);
    }
}
