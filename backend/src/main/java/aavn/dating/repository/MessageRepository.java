package aavn.dating.repository;

import aavn.dating.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import aavn.dating.entity.Message;

import java.util.Collection;

/**
 * Created by lnhien on 6/27/2017.
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Collection<Message> findByUserSend(User user);
    Collection<Message> findByUserReceive(User user);
    Message findByUserSendAndUserReceive(User user1, User user2);
}
