First Create a Proper Database Model for the Microservice

Then Go To Spring Initializer and add the required dependencies
-> Spring Web
-> Spring Data JPA - To Implement the ORM framework using hibernate dialect
-> MySql - To connect with MySQL database
-> Lombok - To reduce the boilerplate code and generate necessary getters and setter
-> Eureka Client - To be able to discovered by other microservices
-> Feign Client - To connect to Other microservice(we can also use the RestTemplate)
-> Validation - To Validate the Data giving to the APIs
-> ModelMapper - To Convert the Entities to DTOs(We can also Use MapStruct)

Then Create the Packages to organize the structure for our project
-> Controller - Create controller Classes
    - The controller class handles incoming HTTP requests and delegates processing to the appropriate service.
    - It contains methods annotated with @RequestMapping, @GetMapping, @PostMapping, etc., to map HTTP requests to specific endpoints.
    - The controller extracts data from the request, calls the appropriate service methods, and returns the HTTP response.
    - It's responsible for handling request validation, error handling, and marshalling/unmarshalling data.
    - Example: UserController, ProductController, OrderController, etc.

-> Entity - Create Entity Classes
    - The entity class represents a domain object or a database table.
    - An entity typically maps to a database table using JPA annotations.
    - It defines the structure of the data and its relationships.
    - Example: User, Product, Order, etc.

-> DTOs - Create DTO 
    - DTOs are used to transfer data between different layers of the application, such as between the controller and service layers.
    - They represent a subset of entity attributes or a combination of attributes from multiple entities.
    - DTOs help decouple the internal representation of data from the external representation exposed by the API.
    - Example: UserDTO, ProductDTO, OrderDTO, etc.
    

-> Repository - Create Repository Interface and Implement JPARepository
    - The repository interface provides CRUD (Create, Read, Update, Delete) operations for interacting with the database.
    - It extends JpaRepository or CrudRepository, which are Spring Data interfaces providing common database operations.
    - By defining methods in the repository interface, you can perform database queries without writing SQL.
    - Example: UserRepository, ProductRepository, OrderRepository, etc.

-> Service - Create Service Interface
    - The service interface defines the contract for business logic operations related to a specific domain.
    - It declares methods representing various functionalities required by the application.
    - Service interfaces provide a layer of abstraction between controllers and data access, promoting modularity and testability.
    - Example: UserService, ProductService, OrderService, etc.

   -> ServiceImplementation - Implement the Service Interface
        - he service implementation class implements the methods defined in the service interface.
        - It encapsulates the application's business logic, including validation, data manipulation, and interaction with repositories.
        - Service implementations are where you handle transactions, orchestrate multiple repository operations, and enforce business rules.
        - Example: UserServiceImpl, ProductServiceImpl, OrderServiceImpl, etc.

Add Database properties to the application.properties file
    -> spring.application.name=UserService
    -> spring.datasource.url=jdbc:mysql://localhost:3306/microservices
    -> spring.datasource.username=root
    -> spring.datasource.password=
    -> spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    -> spring.jpa.hibernate.ddl-auto=update

Add Eureka Server Properties in application.properties file
    ->eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/
