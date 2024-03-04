package edu.syr.textbooks.service;

import edu.syr.textbooks.exception.BookNotFoundException;
import edu.syr.textbooks.model.Book;
import edu.syr.textbooks.model.Transaction;
import edu.syr.textbooks.model.TransactionType;
import edu.syr.textbooks.repository.BookRepository;
import edu.syr.textbooks.repository.TransactionRepository;
import edu.syr.textbooks.util.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl extends BaseService implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book newBook) {
        startTimer();
        Book existingBook = bookRepository.findBookById(newBook.getId());
        if (existingBook != null) {
            throw new IllegalStateException("Book with the same ID already exists.");
        }
        endTimer("addBook");
        return bookRepository.save(newBook);
    }

    @Override
    public Book getBookById(String id) {
        Book book = bookRepository.findBookById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }


    @Override
    public double sellBook(String bookId, String userId) {
        startTimer();
        Book book = getBookById(bookId);

        if (book != null) {
            Transaction transaction = new Transaction();
            transaction.setUserId(userId);
            transaction.setBookId(book.getId());
            transaction.setType(TransactionType.SELL);
            transaction.setPriceAtTransaction(book.getPrice());
            transactionRepository.save(transaction);

            double newPrice = PriceCalculator.calculateDepreciatedPrice(book.getPrice());
            book.setPrice(newPrice);
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);

            //Remove the sold book from the user's list of books
            userServiceImpl.removeBookFromUser(userId, bookId);
            endTimer("sellBook");
            return newPrice;
        }
        endTimer("sellBook");
        return -1.0;
    }

    @Override
    public double sellBookByISBN(String isbn, Book newBookData) {
        Book book = bookRepository.findByIsbn(isbn);

        if (book == null) {
            // Book with the given ISBN doesn't exist, so let's add it to the inventory.
            book = createBookFromData(newBookData, isbn);
            bookRepository.save(book);

            // Perform the transaction for adding to inventory
            Transaction transaction = new Transaction();
            transaction.setBookId(book.getId());
            transaction.setType(TransactionType.SELL);
            transaction.setPriceAtTransaction(book.getPrice());
            transactionRepository.save(transaction);

            return book.getPrice();
        }

        // Existing book logic
        Transaction transaction = new Transaction();
        transaction.setBookId(book.getId());
        transaction.setType(TransactionType.SELL);
        transaction.setPriceAtTransaction(book.getPrice());
        transactionRepository.save(transaction);

        double newPrice = PriceCalculator.calculateDepreciatedPrice(book.getPrice());
        book.setPrice(newPrice);
        book.setQuantity(book.getQuantity() + 1); // Increase quantity
        bookRepository.save(book);

        return newPrice;
    }

    private Book createBookFromData(Book newBookData, String isbn) {
        Book newBook = new Book();
        newBook.setId(newBookData.getId());
        newBook.setISBN(isbn); // Assuming you have a setter for ISBN
        newBook.setAuthors(newBookData.getAuthors());
        newBook.setTitle(newBookData.getTitle());
        newBook.setEdition(newBookData.getEdition());
        newBook.setPrice(newBookData.getPrice());
        newBook.setQuantity(1); // Set the initial quantity to 1

        return newBook;
    }



    @Override
    public boolean buyBook(String bookId, String userId) {
        startTimer();
        long buyTransactionCount = transactionRepository.countByUserIdAndType(userId, TransactionType.BUY);

        if (buyTransactionCount >= 5) {
            //Return false if the user has reached the maximum limit(keeping limit as max of 5 books)
            return false;
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        if (book.getQuantity() > 0) {
            Transaction transaction = new Transaction();
            transaction.setUserId(userId);
            transaction.setBookId(book.getId());
            transaction.setType(TransactionType.BUY);
            transaction.setPriceAtTransaction(book.getPrice());

            transactionRepository.save(transaction);

            double newPrice = PriceCalculator.calculateDepreciatedPrice(book.getPrice());
            book.setPrice(newPrice);
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);

            //Add the book to the user's list of books
            userServiceImpl.addBookToUser(userId, bookId);
            endTimer("buyBook");
            return true;
        } else {
            endTimer("buyBook");
            return false;
        }
    }

    @Override
    public boolean deleteBook(String id) {
        Book book = bookRepository.findBookById(id);

        if (book != null) {
            bookRepository.delete(book);
            return true;
        } else {
            return false;
        }
    }
}