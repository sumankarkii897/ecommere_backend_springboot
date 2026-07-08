#  E-Commerce Backend API

A production-ready **Spring Boot REST API** for an e-commerce platform that provides secure authentication, product management, category management, shopping cart operations, order processing, payment integration, image uploads, and role-based authorization.

The project follows a layered architecture with clean code principles and demonstrates real-world backend development practices using **Spring Boot**, **Spring Security**, **JWT Authentication**, **MySQL**, **Docker**, and cloud deployment.

---
##  Live Demo

**Swagger Documentation**

https://ecommere-backend-springboot.onrender.com/api/v1/swagger-ui/index.html

**GitHub Repository**

https://github.com/sumankarkii897/ecommere_backend_springboot

---

#  Features

### Authentication & Authorization

* JWT-based Authentication
* User Registration & Login
* Role-Based Access Control (Admin/User)
* Password Encryption using BCrypt
* Secure REST APIs with Spring Security

---

### Product Management

* Create Products
* Update Products
* Delete Products
* Get Product Details
* Search Products
* Product Image Upload
* Pagination & Sorting

---

### Category Management

* Create Categories
* Update Categories
* Delete Categories
* Fetch Categories

---

### Shopping Cart

* Add Product to Cart
* Update Cart Quantity
* Remove Product from Cart
* View Cart

---

### Order Management

* Place Orders
* Order History
* Order Details
* Order Status Management

---

### Payment Integration

* Secure Payment API Integration
* Order Verification

---

### Image Upload

* Cloudinary Integration
* Image Storage
* Image URL Management

---

### API Documentation

* Interactive Swagger UI
* REST API Testing
* OpenAPI Documentation

---

# 🏗 Architecture

The project follows a layered architecture.

```
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
MySQL Database
```

Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 ├── service
 ├── util
 └── EcommerceApplication
```

---

#  Tech Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Security

* Spring Security
* JWT Authentication
* BCrypt Password Encoder

### Database

* Postgresql

### Documentation

* Swagger / OpenAPI

### Cloud & Deployment

* Docker
* Render

### Image Storage

* Cloudinary

### Build Tool

* Maven

### Version Control

* Git
* GitHub

---

#  Security Features

* JWT Token Authentication
* Stateless Authentication
* Protected REST Endpoints
* Role-Based Authorization
* Password Encryption
* Custom Authentication Filter
* Exception Handling

---

#  REST APIs

### Authentication

```
POST /auth/register
POST /auth/login
```

### Products

```
GET    /products
GET    /products/{id}
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

### Categories

```
GET    /categories
POST   /categories
PUT    /categories/{id}
DELETE /categories/{id}
```

### Cart

```
GET    /cart
POST   /cart
PUT    /cart
DELETE /cart/{id}
```

### Orders

```
POST   /orders
GET    /orders
GET    /orders/{id}
```

---

#  Running Locally

## Clone Repository

```bash
git clone https://github.com/sumankarkii897/ecommere_backend_springboot.git
```

```bash
cd ecommere_backend_springboot
```

---

## Configure Environment

Update your `application.properties` (or environment variables):

```properties
DB_URL=jdbc:mysql://localhost:3306/your_database
DB_USER=root
DB_PASSWORD=your_password

JWT_SECRET=your_secret

CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
```

---

## Build Project

```bash
mvn clean install
```

---

## Run

```bash
mvn spring-boot:run
```

Application runs on

```
http://localhost:8080
```

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

#  Docker

Build Docker Image

```bash
docker build -t ecommerce-backend .
```

Run Container

```bash
docker run -p 8080:8080 ecommerce-backend
```

---

# 📖 API Documentation

Swagger UI provides complete documentation of all endpoints, request bodies, authentication, and response models.

```
https://ecommere-backend-springboot.onrender.com/api/v1/swagger-ui/index.html
```

---

#  Highlights

* Clean layered architecture
* Production-style REST APIs
* JWT Authentication
* Role-Based Authorization
* Spring Security
* Dockerized Deployment
* Cloudinary Image Upload
* Exception Handling
* Validation
* Swagger Documentation
* MySQL Persistence
* Maven Build
* Cloud Deployment on Render

---

#  Learning Outcomes

This project demonstrates practical experience with:

* Enterprise REST API development
* Authentication & Authorization
* Secure Backend Development
* Database Design
* API Documentation
* Docker Containerization
* Cloud Deployment
* Spring Boot Best Practices
* Clean Architecture
* Backend Software Engineering

---

#  Author

**Suman Karki**

If you found this project useful, consider giving it a ⭐ on GitHub.
