//package aavn.dating.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import aavn.dating.entity.Book;
//import aavn.dating.service.BookService;
//
///**
// * Created by lmchuc on 6/2/2017.
// */
//
//@RestController
//@RequestMapping(value = "/api/books")
//public class BookController {
//    @Autowired
//    private BookService bookService;
//
//    @GetMapping()
//    public ResponseEntity getAllBooks() {
//        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
//    }
//
//    @PostMapping()
//    public ResponseEntity createNewBook(@Validated @RequestBody Book book) {
//        Book todo = bookService.createNewBook(book);
//        return new ResponseEntity<>(todo, HttpStatus.CREATED);
//    }
//
//    @PatchMapping(value = "/{id}")
//    public ResponseEntity updateTodo(@PathVariable("id")  long id) {
//        return new ResponseEntity<>(bookService.updateBook(id), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity deleteTodo(@PathVariable("id")  long id) {
//        bookService.deleteBook(id);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//}
