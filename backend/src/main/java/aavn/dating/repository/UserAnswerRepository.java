package aavn.dating.repository;

import aavn.dating.entity.User;
import aavn.dating.entity.User_Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by dhvy on 6/20/2017.
 */
@Repository
public interface UserAnswerRepository extends CrudRepository<User_Answer, Long>
{
    Collection<User_Answer> findByUser(User user);
}
