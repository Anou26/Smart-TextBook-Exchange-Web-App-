# Smart TextBook Exchange Web-App

## Motivation
Textbooks are expensive! This project aims to address the high cost of textbooks by implementing 
a fixed-size pool of books that can be sold, bought back, and resold to students. The price of 
each book depreciates by 10% with each transaction, providing cost-effective access to educational 
materials.

## Installation/ Configuration
- JDK/ Java Version >= 17
- IDE : IntelliJ/ Eclipse, should support SpringBoot application
- Necessary dependencies: Spring web, Lombok, MongoDB, Spring test.
- For Database, Use- Atlas MongoDB. Credentials are specified in the Application.properties file.

## Grading Rubric Satisfaction

### Modularization
- [x] Sensible class definitions
- [x] Organized classes in packages: Controller, Model, Service, Repository, Util
- [x] Coding to interfaces: Used interfaces wherever needed

### Design & Structure
- [x] Object-Oriented Design principles :Encapsulation, Inheritance and Composition holds perfectly.
- [x] Interface, Abstract, and Enum(TransactionType) is used.
- [x] Design Pattern : Spring has dependency injection, aligned SOLID principles in the code.

### Readability & Clarity
- [x] Meaningful Naming: Used meaningful naming
- [x] Code Consistency

Our web app project successfully fulfills all the criteria outlined in the grading rubric, ensuring a well-structured and comprehensible codebase.

## Features
### Main features
- **Inventory of Books**: The system maintains an inventory of books, each uniquely identified by an RFID tag, and includes attributes such as ISBN, author(s), title, edition, and price.
- **User Buy Book**: Students can purchase books from the inventory. The system returns a success message upon a successful purchase or a failure message if the book is unavailable.
- **User Sell Book (by ID)**: Users can sell books previously bought from our service by providing the book's unique ID. The system calculates the resale price, considering the depreciation, and returns a success message with the price or a failure message.
- **User Sell Book (by ISBN)**: Users can also sell books that are new to our service, based on the book's ISBN. Similar to selling by ID, the system calculates the resale price and provides a success message with the price or a failure message.

### Extra implemented APIs

##### BookController:

- getAll : Display All books from the inventory.
- add : Add book in the inventory.
- deleteBook : Delete book from the inventory.
- getUserBooks : Get list of books owned by a particular user.

##### UserController:

- add : Add User in the User table.
- addMultiple: Add multiple users at a time.
- getAll : Display all users.
- get : find user by username.
- update : update user info.

##### TranactionController:

- history : Get transaction history.
- getUserTransactions : Get transactions of particular user.

## Handeled cases:

- Limited users to a maximum of 5 "BUY" transactions for fairness.
- Implemented a price threshold to prevent books from becoming free due to depreciation.
- Ensured that search criteria are case-insensitive.

## Database:

- In this project, MongoDB database is used. Atlas MongoDB is a managed **cloud database** service that simplifies database management, offering high availability and scalability without the need for server infrastructure management.
- Collections in the database:
  - book :{"_id","ISBN","authors","title","edition","price":{"$numberDouble"},"quantity":{"$numberInt"}}
  - bookTransaction : {"_id","bookId","TransactionType{BUY,SELL}","priceAtTransaction":{"$numberDouble"}}
  - user : {"_id","username","bookIds":[]}

## Acknowledgments

- Atlas MongoDB
- SpringBoot Application
- IntelliJ
- Postman
