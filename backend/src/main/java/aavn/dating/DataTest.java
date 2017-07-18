package aavn.dating;

import aavn.dating.entity.Question;
import aavn.dating.entity.User;
import aavn.dating.entity.User_Answer;
import aavn.dating.repository.QuestionRepository;
import aavn.dating.repository.UserAnswerRepository;
import aavn.dating.repository.UserRepository;
import aavn.dating.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by dhvy on 6/9/2017.
 */
@Component
public class DataTest implements CommandLineRunner{

    @Autowired
    private QuestionRepository quesRepository;
    private final UserRepository userRepository;

    @Autowired
    private  UserAnswerRepository answerRepository;
    @Autowired
    public DataTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        findAll();
        List<String> quiz = new ArrayList<>();
        quiz.add("I always get my work done before relaxing.");
        quiz.add("I rarely worry about how actions affect other people.");
        quiz.add("Others would describe me as outgoing and friendly.");
        quiz.add("I would rather be called practical than inventive.");
        quiz.add("My workspace is often messy.");
        quiz.add("I prefer being an engineer to being an designer.");
        quiz.add("I have a hard time making decisions");
        quiz.add("Others would describe me describe me as a nice-looking person.");
        quiz.add("I can start conversations easily even with someone I just met.");
        quiz.add("I am more interested in romantic persons than smart ones.");
        quiz.add("I like to make lists to help my memory.");
        quiz.add("I like to follow my head more than my heart.");
        quiz.add("I believe in fate or destiny.");
        quiz.add("I focus on the future, rather than the here-and-now.");
        quiz.add("The more people I speak to, the better I feel.");
        quiz.add("I work best when I'm alone.");
        quiz.add("I are easily affected by strong emotions");
        quiz.add("I am not interested in theoretical discussions.");
        quiz.add("Often you prefer to read a book than go to a party");
        quiz.add("I prefer to take a multiple choice test.");


        for ( int i = 0; i < quiz.size(); i++ )
        {
            Question ques = new Question();
            ques.setContent(quiz.get(i));
            quesRepository.save(ques);

        }
        Random rand = new Random();

        for ( int i = 0; i < 20; i++)
        {
//            UserService service = new UserServiceImp();

            User newUser = addUser();

            for( int j = 0 ; j < 20; j++) {
                User_Answer test = new User_Answer();
                test.setUser(newUser);
                test.setQues(new Question(j+1));
                test.setAns(rand.nextInt(5));

                if (test!= null)
                    answerRepository.save(test);
            }
        }


    }

    private void findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        for ( User user : users )
        {
            System.out.println("test" + user.getEmail());
        }
    }

    public User addUser() throws ParseException {
        Faker faker = new Faker();
        User user = new User();
        String email = faker.internet().emailAddress();
        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String address = faker.address().city();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String from = "1980-12-12";
        String  to = "1996-12-12";
        Date fromDate = format.parse(from);
        Date toDate = format.parse(to);
        Date birthday = faker.date().between(fromDate, toDate);
        String avaImg = faker.internet().avatar();

        user.setEmail(email);
        user.setPassword("$31$16$GJAISEw3kS3e0j1UMnTQsw3G8xVrRf3wAFjNyG75_4A");
        user.setName(name);
        user.setAddress(address);
        user.setBirthday(birthday);
        user.setAvaImg(avaImg);

        return addUser(user);
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

}