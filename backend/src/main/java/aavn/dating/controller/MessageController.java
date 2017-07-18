package aavn.dating.controller;

import aavn.dating.entity.Appointment;
import aavn.dating.entity.Message;
import aavn.dating.entity.User;
import aavn.dating.service.MessageService;
import aavn.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by lnhien on 6/27/2017.
 */
@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity sendMessage(@Validated @RequestBody Message message) {
        Message msg = null;

        if ( messageService.findByUserSendAndUserReceive(message.getUserSend(), message.getUserReceive()) == null )
            msg = messageService.saveMessage(message);
        if (msg == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getAllMessages(@PathVariable("id") Long id) {
        User userRes = userService.findById(id);
        if (userRes == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            Collection<Message> messRes = messageService.findByUserSend(userRes);
            if ( messRes != null){
                for ( Message mess : messRes)
                    System.out.println("Duong Hoang Vy" + mess);
                return new ResponseEntity<>(messRes, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity getMessage(@PathVariable("id") Long id, @Validated @RequestBody String userReceiveId) {
        System.out.println("come here");
        System.out.println("user Receive Id: "+ userReceiveId);
        User userSend = userService.findById(id);
        User userReceive = userService.findById( Long.parseLong(userReceiveId) );
        Message msg = messageService.findByUserSendAndUserReceive(userSend, userReceive);
        System.out.println("Message: " + msg);
        if (msg == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            System.out.println("not null");
            return new ResponseEntity<>( msg, HttpStatus.OK);
        }
    }

}
