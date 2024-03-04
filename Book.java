package edu.syr.textbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("book")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @MongoId
    private String id;

    private String ISBN;
    private String authors;
    private String title;
    private String edition;
    private double price;
    private int quantity;
}
