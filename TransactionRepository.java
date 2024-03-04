package edu.syr.textbooks.repository;

import edu.syr.textbooks.model.Transaction;
import edu.syr.textbooks.model.TransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUserId(String userId);

    long countByUserIdAndType(String userId, TransactionType type);
}
