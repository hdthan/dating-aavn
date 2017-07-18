package aavn.dating.service;

import aavn.dating.entity.Message;
import aavn.dating.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by lnhien on 6/27/2017.
 */
public interface MessageService {
    Message saveMessage(Message message);
    Collection<Message> findByUserSend(User user);
    Collection<Message> findByUserReceive(User user);
    Message findByUserSendAndUserReceive(User user1, User user2);
}
