package aavn.dating.service;

import aavn.dating.entity.User;
import aavn.dating.entity.User_Answer;
import aavn.dating.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;

/**
 * Created by dhvy on 6/20/2017.
 */
@Service
@Transactional
public class UserAnswerServiceImp implements UserAnswerService {

    @Autowired
    UserAnswerRepository repository;
    @Override
    public User_Answer addAnswer(User_Answer user_answer) {
        User_Answer res = null;

        //filter before persist to database
        try {
            res = repository.save(user_answer);
        }
        catch (UnexpectedRollbackException ex )
        {
            if(ex.getMostSpecificCause() instanceof SQLIntegrityConstraintViolationException){
                System.out.println(ex);
                return null;
            }
        }


        return res;
    }

    @Override
    public Collection<User_Answer> getAnswerByUser(User user) {
        return repository.findByUser(user);
    }
}
