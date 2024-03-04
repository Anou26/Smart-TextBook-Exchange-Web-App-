package edu.syr.textbooks.service;

import edu.syr.textbooks.model.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionHistory();
}
