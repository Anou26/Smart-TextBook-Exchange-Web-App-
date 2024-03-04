package edu.syr.textbooks.controller;

import edu.syr.textbooks.model.Book;
import edu.syr.textbooks.service.BookServiceImpl;
import edu.syr.textbooks.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookServiceImpl.getBooks();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book newBook) {
        return bookServiceImpl.addBook(newBook);
    }

    @PostMapping("/sell/{bookId}/{userId}")
    public ResponseEntity<String> sellBook(@PathVariable String bookId, @PathVariable String userId) {
        double newPrice = bookServiceImpl.sellBook(bookId, userId);
        if (newPrice >= 0) {
            return new ResponseEntity<>("Success: you sold the book. New price: " + newPrice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failure: unable to sell the book.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sell/isbn/{isbn}")
    public ResponseEntity<String> sellBookByISBN(@PathVariable String isbn, @RequestBody Book newBookData) {
        double price = bookServiceImpl.sellBookByISBN(isbn, newBookData);

        if (price != -1.0) {
            return new ResponseEntity<>("Success. Price: $" + price, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("--Failure", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/buy/{bookId}/{userId}")
    public ResponseEntity<String> buyBook(@PathVariable String bookId, @PathVariable String userId) {
        if (bookServiceImpl.buyBook(bookId, userId)) {
            return new ResponseEntity<>("Success: you bought the book!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failure: Buying restricted", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}/getUserBooks")
    public List<Book> getUserBooks(@PathVariable String userId) {
        return userServiceImpl.getUserBooksById(userId);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        boolean result = bookServiceImpl.deleteBook(id);
        if (result) {
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete book", HttpStatus.NOT_FOUND);
        }
    }
}