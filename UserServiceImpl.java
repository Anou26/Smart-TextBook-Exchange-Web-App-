package edu.syr.textbooks.service;

import edu.syr.textbooks.exception.UserNotFoundException;
import edu.syr.textbooks.model.Book;
import edu.syr.textbooks.model.Transaction;
import edu.syr.textbooks.model.User;
import edu.syr.textbooks.repository.BookRepository;
import edu.syr.textbooks.repository.TransactionRepository;
import edu.syr.textbooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public User createUser(User user) {
        User existingUser = userRepository.findUserByUsernameIgnoreCase(user.getUsername());
        if (existingUser != null) {
            throw new IllegalStateException("User with the same username already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        startTimer();
        User user = userRepository.findUserByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        endTimer("getUser");
        return user;
    }

    @Override
    public User updateUser(String username, User updatedUser) {
        User user = getUser(username);
        user.setUsername(updatedUser.getUsername());
        user.setBookIds(updatedUser.getBookIds());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Book> getUserBooksById(String userId) {
        startTimer();
        List<Transaction> userTransactions = transactionRepository.findByUserId(userId);
        List<String> bookIds = userTransactions.stream()
                .map(Transaction::getBookId)
                .collect(Collectors.toList());
        List<Book> userBooks = new ArrayList<>(bookRepository.findAllById(bookIds));
        endTimer("getUserBooksById");
        return userBooks;
    }

    @Override
    public List<Transaction> getUserTransactions(String userId) {
        return transactionRepository.findByUserId(userId);
    }

    @Override
    public void removeBookFromUser(String userId, String bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<String> userBookIds = user.getBookIds();
        userBookIds.remove(bookId);
        user.setBookIds(userBookIds);
        userRepository.save(user);
    }

    @Override
    public void addBookToUser(String userId, String bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<String> userBookIds = user.getBookIds();
        userBookIds.add(bookId); // Add the book ID to the user's list
        user.setBookIds(userBookIds);
        userRepository.save(user);
    }
}
