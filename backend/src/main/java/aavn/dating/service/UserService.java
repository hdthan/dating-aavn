package aavn.dating.service;

import aavn.dating.entity.User;

import java.util.List;

/**
 * Created by dhvy on 6/8/2017.
 */
public interface UserService {
    List<User> findAll();

    User findByEmail(String email);

    User save(User user );

    User findUser( User user);

    User updateUser( User user );

    User findById( long id);
}
