package aavn.dating.repository;

import aavn.dating.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dhvy on 6/8/2017.
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
