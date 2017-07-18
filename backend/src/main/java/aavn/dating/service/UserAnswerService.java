package aavn.dating.service;

import aavn.dating.entity.User;
import aavn.dating.entity.User_Answer;

import java.util.Collection;

/**
 * Created by dhvy on 6/20/2017.
 */
public interface UserAnswerService {
    User_Answer addAnswer( User_Answer user_answer);

    Collection<User_Answer> getAnswerByUser(User user);
}
