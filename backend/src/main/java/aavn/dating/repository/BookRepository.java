package aavn.dating.repository;

import aavn.dating.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lmchuc on 6/2/2017.
 */
@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
}
