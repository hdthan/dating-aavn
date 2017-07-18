//package aavn.dating.service;
//
//import aavn.dating.entity.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import aavn.dating.repository.BookRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by lmchuc on 6/2/2017.
// */
//@Service
//@Transactional
//public class BookServiceImpl implements BookService {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Override
//    public Book createNewBook(Book book) {
//        return bookRepository.save(book);
//    }
//
//    @Override
//    public Book updateBook(long bookId) {
//        return bookRepository.save(bookRepository.findOne(bookId));
//    }
//
//    @Override
//    public void deleteBook(long bookId) {
//        bookRepository.delete(bookId);
//    }
//
//    @Override
//    public List<Book> findAllBooks() {
//        List<Book> books = new ArrayList<>();
//        bookRepository.findAll().forEach(books::add);
//        return books;
//    }
//}
