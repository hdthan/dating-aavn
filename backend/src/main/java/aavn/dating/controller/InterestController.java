package aavn.dating.controller;

import aavn.dating.entity.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dhvy on 6/27/2017.
 */
@RestController
@RequestMapping( value = "api/interest")
public class InterestController {

    @PostMapping
    public ResponseEntity interest(@Validated @RequestBody Message message){
        return null;
    }
}
