package aavn.dating.controller;

import aavn.dating.entity.Message;
import aavn.dating.entity.User;
import aavn.dating.service.MessageService;
import aavn.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by nhmnguyen on 6/28/2017.
 */
@RestController
@RequestMapping(value = "/api/received_message")
public class MessageReceiveController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getAllMessages(@PathVariable("id") Long id) {
        User userRes = userService.findById(id);
        if (userRes == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            Collection<Message> messRes = messageService.findByUserReceive(userRes);
            if ( messRes != null){
                for ( Message mess : messRes)
                    System.out.println("Duong Hoang Vy" + mess);
                return new ResponseEntity<>(messRes, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
