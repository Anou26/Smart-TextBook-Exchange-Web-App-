package edu.syr.textbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("bookTransaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

        @MongoId
        private String transactionId;

        private String userId;
        private String bookId;
        private TransactionType type;
        private double priceAtTransaction;
}
