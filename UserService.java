package edu.syr.textbooks.service;

import edu.syr.textbooks.exception.UserNotFoundException;
import edu.syr.textbooks.model.Book;
import edu.syr.textbooks.model.Transaction;
import edu.syr.textbooks.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(String username) throws UserNotFoundException;
    User updateUser(String username, User updatedUser);
    List<User> getAllUsers();
    List<Book> getUserBooksById(String userId);
    List<Transaction> getUserTransactions(String userId);
    void removeBookFromUser(String userId, String bookId);

    void addBookToUser(String userId, String bookId);
}
