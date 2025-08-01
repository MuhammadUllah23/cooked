// // Path: src/test/java/com/example/cooked_backend/integrationTests/containers/AbstractPostgresContainerTest.java
// package com.example.cooked_backend.containers;

// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.test.context.DynamicPropertyRegistry;
// import org.springframework.test.context.DynamicPropertySource;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.testcontainers.containers.PostgreSQLContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;

// @Testcontainers
// @ExtendWith(SpringExtension.class)
// public abstract class AbstractPostgresContainerTest {

//     @Container
//     protected static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
//             .withDatabaseName("testdb")
//             .withUsername("test")
//             .withPassword("test");

//     @DynamicPropertySource
//     static void configureProperties(DynamicPropertyRegistry registry) {
//         registry.add("spring.datasource.url", postgres::getJdbcUrl);
//         registry.add("spring.datasource.username", postgres::getUsername);
//         registry.add("spring.datasource.password", postgres::getPassword);
//         registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop"); // ✅ this line
//         registry.add("spring.jpa.show-sql", () -> "true");
//     }
// }
