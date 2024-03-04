
package edu.syr.textbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @MongoId
    private String id;
    private String username;
    private List<String> bookIds = new ArrayList<>(); // List of book IDs currently owned by the user

}
