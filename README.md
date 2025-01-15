# Multi-Database Application

This is a Spring Boot application designed to handle multiple databases (MySQL and MongoDB) seamlessly. The application manages orders stored in MongoDB and products stored in MySQL.

## Features

- **MySQL Integration:** Manage product details using MySQL.
- **MongoDB Integration:** Manage order details using MongoDB.
- **REST APIs:** CRUD operations for orders and products.
- **Entity Relationships:** Products are associated with orders by maintaining references.

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- MySQL database
- MongoDB server
- Postman or any REST client (optional, for API testing)

## Installation and Setup

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-repo/multi-database-app.git
cd multi-database-app
```

### Step 2: Configure Databases

#### MySQL
- Create a MySQL database named `product_management`.
- Update `application.properties` with your MySQL username and password.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_management
spring.datasource.username=root
spring.datasource.password=abcd
```

#### MongoDB
- Ensure MongoDB is running locally.
- The application uses a database named `OrderDB`. No manual creation is needed; MongoDB will create it automatically.

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/OrderDB
```

### Step 3: Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

### Step 4: Access the Application
- The application runs on `http://localhost:8080`.

## API Endpoints

### Orders API (MongoDB)

| Method | Endpoint          | Description                      |
|--------|-------------------|----------------------------------|
| POST   | `/orders`         | Create a new order              |
| GET    | `/orders`         | Retrieve all orders             |
| GET    | `/orders/{id}`    | Retrieve an order by ID         |
| DELETE | `/orders/{id}`    | Delete an order by ID           |

### Products API (MySQL)

| Method | Endpoint          | Description                      |
|--------|-------------------|----------------------------------|
| POST   | `/products`       | Create a new product            |
| GET    | `/products`       | Retrieve all products           |
| GET    | `/products/{id}`  | Retrieve a product by ID        |
| DELETE | `/products/{id}`  | Delete a product by ID          |

## Technologies Used

- **Backend:** Spring Boot, Spring Data JPA, Spring Data MongoDB
- **Database:** MySQL, MongoDB
- **Build Tool:** Maven

## Project Structure

```plaintext
src/main/java
├── com.Qloron.MutliDatabase
│   ├── configuration
│   │   ├── MongoDBConfig.java
│   │   ├── MySQLConfig.java
│   ├── controller
│   │   ├── OrderController.java
│   │   ├── ProductController.java
│   ├── model
│   │   ├── Order.java
│   │   ├── Product.java
│   ├── repository
│   │   ├── OrderRepository.java
│   │   ├── ProductRepository.java
│   ├── service
│       ├── OrderService.java
│       ├── ProductService.java
application.properties
```

## Known Issues

- Ensure both databases are running before starting the application.
- ID format consistency between MongoDB and MySQL requires attention.

## Contributing

Feel free to fork the repository and submit pull requests. Ensure your code adheres to the existing coding standards.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or support, contact [1tusharnair@gmail.com].
