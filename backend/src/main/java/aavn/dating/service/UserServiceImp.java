package aavn.dating.service;

import aavn.dating.entity.User;
import aavn.dating.repository.UserRepository;
import aavn.dating.utility.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by dhvy on 6/9/2017.
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordAuthentication passAuthenticate;

    @Override
    public List<User> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public User save(User user) {
        User user_res = null;

        //filter before persist to database
        user.setDefault();
        if ( user.getPassword() != null )
            user.setPassword( passAuthenticate.hash( user.getPassword().toCharArray()));

        try {
            user_res = userRepository.save(user);
        }
        catch (UnexpectedRollbackException ex )
        {
            if(ex.getMostSpecificCause() instanceof SQLIntegrityConstraintViolationException){
                System.out.println(ex);
                return null;
            }
        }


        return user_res;
    }

    @Override
    // if input user had id, find by id, if user has password, authenticate password , if user doesn't have password, return result , else, find by email and password
    //hmm, maybe not good
    public User findUser(User user) {

        if ( user.getUserId() > 0  )
        {
            try {
                User res = userRepository.findOne(user.getUserId() ) ;
                return res;
            } catch (NullPointerException ex )
            {
                return null;
            }
        }
        if ( user.getEmail() != null ) {

            User res = this.findByEmail(user.getEmail());
            // authenticate password
            try {
                if ((res.getPassword() != null && passAuthenticate.authenticate(user.getPassword().toCharArray(), res.getPassword())))
                    return res;
                else
                    return null;
            } catch (NullPointerException ex) {
                return null;
            }
        }

        return null;
    }

    @Override
    public User updateUser(User user) {
        User user_res = this.findUser( user );
        System.out.println();
        if (user.getPassword() != null && user.getPassword() != "" )
        {
            if ( passAuthenticate.authenticate( user.getPassword().toCharArray(), user_res.getPassword())) {
                System.out.println("confirm pass" + user.getConfirmPassword());
                user_res.setPassword( passAuthenticate.hash( user.getConfirmPassword().toCharArray() ) );

                return user_res;
            }
            else
                return null;
        }
        if ( user_res != null )
        {
            user_res.updateUser(user);
            userRepository.save(user_res);
        }
        else
            return null;
        return user_res;
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }
}
