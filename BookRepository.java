package edu.syr.textbooks.repository;

import edu.syr.textbooks.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findBookById(String id);
    @Query("{ 'ISBN' : ?0 }")
    Book findByIsbn(String isbn);
}