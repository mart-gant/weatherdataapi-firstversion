# Weather Data API

Application for managing weather data using a MySQL database and WireMock for mocking external services.

## Requirements

- Docker and Docker Compose
- Java 17 or later
-Maven

## Configuration and startup

1. **Clone repository:**

 ```bash
 git clone https://github.com/yourusername/weatherdataapi.git
 cd weatherdataapi
 ```

2. **Start MySQL database:**

 Make sure you are in the root of your project where `docker-compose.yml` is located. Then run:

 ```bash
 docker-compose up -d
 ```

 This will start the MySQL database on port 3306 with the default configuration:
 - `username`: `user`
 - `password`: `userpassword`
 - `database`: `weatherdata`

3. **Configure the application:**

 Check the `src/main/resources/application.properties` file to ensure the database configuration is correct:

 ```properties
 # MySQL Database Configuration
 spring.datasource.url=jdbc:mysql://localhost:3306/weatherdata
 spring.datasource.username=user
 spring.datasource.password=userpassword
 spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

 #JPAConfiguration
 spring.jpa.hibernate.ddl-auto=update
 spring.jpa.show-sql=true
 spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
 ```

4. **Run the Spring Boot application:**

 Use the command below to start your Spring Boot application:

 ```bash
 ./mvnw spring-boot:run
 ```

 The application should be available at [http://localhost:8080](http://localhost:8080).

5. **Application Check:**

 The application should be available at [http://localhost:8080](http://localhost:8080).

6. **Testing:**

 To run tests:

 ```bash
 ./mvnw test
 ```
