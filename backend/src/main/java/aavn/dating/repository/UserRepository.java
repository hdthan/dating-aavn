package aavn.dating.repository;

import aavn.dating.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lmchuc on 6/2/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
