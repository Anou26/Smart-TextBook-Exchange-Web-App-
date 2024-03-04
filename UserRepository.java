package edu.syr.textbooks.repository;

import edu.syr.textbooks.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
 //   User findByUsername(String username);

    User findUserByUsernameIgnoreCase(String username);
}
