package edu.syr.textbooks.service;

import edu.syr.textbooks.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book addBook(Book newBook);
    Book getBookById(String id);
    double sellBook(String bookId, String userId);
//    double sellBookByISBN(String isbn);

    double sellBookByISBN(String isbn, Book newBookData);

    boolean buyBook(String bookId, String userId);
    boolean deleteBook(String id);
}
