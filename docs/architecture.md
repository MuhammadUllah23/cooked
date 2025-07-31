## 🏗️ Architecture Overview

The Cooked backend is built using a modular, scalable Spring Boot architecture and is containerized with Docker. Below is a high-level description of the system components:

### 🔙 Backend (Spring Boot)

The core of the application is a Spring Boot backend that exposes RESTful APIs. It is structured using a layered architecture:
- **Controllers** handle HTTP requests and route them to appropriate services.
- **Service Layer** contains all business logic.
- **Repository Layer** interacts with the PostgreSQL database using Spring Data JPA.

### 🔐 Authentication

The application uses **Spring Security** with **JWT (JSON Web Tokens)** for stateless authentication. Upon login, a JWT is issued and included in headers for subsequent requests.

### 🗃️ Database & Migrations

Data is stored in a **PostgreSQL** database. Database schema is managed using **Flyway**, which runs versioned `.sql` migration files automatically at application startup.

### ⚡ Caching

A **Redis** cache is used to store frequently accessed data, reducing the need for repeated database queries and improving overall performance.

### 🐳 Dockerized Stack

The entire stack runs in a **Docker Compose** environment to ensure consistency across development and deployment. Services include:
- `spring-boot-app` (Backend API)
- `postgres` (Database)
- `redis` (Cache)

### 🧩 System Architecture Diagram

<p align="center">
  <img src="./assets/Cooked Architecture.drawio.png" width="600" alt="Cooked Architecture Diagram" />
</p>

---

## 📌 Related Documentation

- [README](../README.md)
- [API Reference](./api-spec.md)
- [Setup & Installation Guide](./setup.md)
- [User Guide](./user-guide.md)
- [Database Schema](./database-schema.md)
