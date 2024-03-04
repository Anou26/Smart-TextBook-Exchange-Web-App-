package edu.syr.textbooks.controller;

import edu.syr.textbooks.model.Transaction;
import edu.syr.textbooks.service.TransactionServiceImpl;
import edu.syr.textbooks.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/history")
    public ResponseEntity<List<Transaction>> getTransactionHistory() {
        List<Transaction> history = transactionServiceImpl.getTransactionHistory();
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{userId}/transactions")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        return userServiceImpl.getUserTransactions(userId);
    }
}
