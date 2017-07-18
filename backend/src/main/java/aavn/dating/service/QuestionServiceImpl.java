package aavn.dating.service;

import aavn.dating.entity.Question;
import aavn.dating.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lnhien on 6/20/2017.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        System.out.println(questions);
        return questions;
    }
}
