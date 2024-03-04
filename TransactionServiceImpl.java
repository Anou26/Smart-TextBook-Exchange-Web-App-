package edu.syr.textbooks.service;

import edu.syr.textbooks.model.Transaction;
import edu.syr.textbooks.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionHistory() {
        return transactionRepository.findAll();
    }
}
