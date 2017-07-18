package aavn.dating.controller;

import aavn.dating.entity.User;
import aavn.dating.service.TokenService;
import aavn.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dhvy on 6/13/2017.
 */
@RestController
@RequestMapping(value = "/api/social_login")
public class SocialLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(consumes = "text/plain")
    public ResponseEntity confirmUser( @RequestBody  String token) {
        System.out.println("you come here");
        System.out.println("token result" + token);

        User user_res = tokenService.getTokenFromGoogle(token); // fix code here, not clearly
        return new ResponseEntity<>(user_res, HttpStatus.CREATED);
    }
}
