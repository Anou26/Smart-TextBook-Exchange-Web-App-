package edu.syr.textbooks.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User Not found with username: "+ username);
    }

}