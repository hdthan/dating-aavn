//package aavn.dating;
//
//import aavn.dating.entity.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import aavn.dating.repository.BookRepository;
//
///**
// * Created by lmchuc on 6/2/2017.
// */
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final BookRepository bookRepository;
//
//    @Autowired
//    public DataLoader(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        addBook("Markus Heitz", "Die Zwerge");
//        addBook("Nassim Nicholas Taleb", "Der Schwarze Schwan");
//        addBook("Stefan Merath", "Der Weg zum erfolgreichen Unternehmer");
//        addBook("Stephen R. Covey und Angela Roethe", "Die 7 Wege zur Effektivität");
//        addBook("Jens-Uwe Meyer", "Das Edison-Prinzip");
//        addBook("Joe Abercrombie ", "The first Law");
//        addBook("Daniel Kahneman", "Schnelles Denken Langsames Denken");
//        addBook("Napoleon Hill", "Denke nach und werde reich");
//    }
//
//    public Book addBook(String author, String title) {
//        Book book = new Book();
//        book.setAuthor(author);
//        book.setTitle(title);
//        return addBook(book);
//    }
//
//    public Book addBook(Book book) {
//        bookRepository.save(book);
//        return book;
//    }
//
//}
