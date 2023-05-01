
# Pilotes Order Management
Pilotes of the great Miquel Montoro are a Majorcan recipe that consisting of a meatball stew.

Pilotes Order Management is a web-based order management system built with Java 18, Spring Boot, lombok and an in-memory H2 database.
The system allows you to create and manage customer orders, fetch customer information, and calculate order totals.

## Features

- Create Customer with unique telephone number
- Create and manage customer orders within 5 minutes
- Calculate order totals
- Search orders if authorized

## Getting Started
- run the application and navigate to
[http://localhost:8280/swagger-ui.html](http://localhost:8280/swagger-ui.html)

- Login with user/user or admin/admin (for order searches)

- Create a customer
- Insert Order for that customer
- Search and manage customer orders

## Local Development

### Prerequisites

- Java 18
- Maven
- Docker and Docker Compose (optional)

### Run the application without Docker

1. Build the project:`

mvn clean package


`2. Start the application:`

mvn spring-boot:run

`3. Access the application on your browser or API client at:`

[http://localhost:8280](http://localhost:8280/)


### Run the application with Docker Compose

1. Build the project:`

mvn clean package

`2. Run the Docker Compose:`

docker-compose up --build

`3. Access the application on your browser or API client at:`

[http://localhost:8280](http://localhost:8280/)


``4. To stop and remove containers, networks, and volumes defined in the `docker-compose.yml`, run:``

docker-compose down

## API Documentation

The API documentation is provided using Swagger UI. After running the application, you can access the API documentation at:`

[http://localhost:8280/swagger-ui.html](http://localhost:8280/swagger-ui.html)

## Contributing :)

If you'd like to contribute to the project, please follow the steps below:

1. Fork the project repository
2. Create a feature branch
3. Commit your changes
4. Push your branch to your fork
5. Create a pull request to the main project repository

Please ensure your code follows best practices, and include tests for your changes.

## License
This project is licensed under the Jaagad Digital Company, For more information, write to danilo.bruschetti@gmail.com
