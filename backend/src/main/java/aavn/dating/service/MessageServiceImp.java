package aavn.dating.service;

import aavn.dating.entity.Message;
import aavn.dating.entity.User;
import aavn.dating.repository.MessageRepository;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lnhien on 6/27/2017.
 */
@Service
//@Transactional
public class MessageServiceImp implements MessageService{
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message saveMessage(Message message) {
        Message messRes = null;
        try {
            messRes = messageRepository.save(message);
        }catch (UnexpectedRollbackException ex)
        {
            return null;
        }

        return messageRepository.save(message);
    }

    @Override
    public Collection<Message> findByUserSend(User user) {
        Collection<Message> messSend = messageRepository.findByUserSend(user);
        return messSend;
    }

    @Override
    public Collection<Message> findByUserReceive(User user) {
        Collection<Message> messReceive = messageRepository.findByUserReceive(user);
        return messReceive;
    }

    @Override
    public Message findByUserSendAndUserReceive(User user1, User user2) {
        return (Message) messageRepository.findByUserSendAndUserReceive(user1, user2);
    }

}
