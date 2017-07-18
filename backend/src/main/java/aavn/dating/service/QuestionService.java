package aavn.dating.service;

import aavn.dating.entity.Question;

import java.util.List;

/**
 * Created by lnhien on 6/20/2017.
 */
public interface QuestionService {
    List<Question> findAll();
}
