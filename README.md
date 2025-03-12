# Multi-Tenant E-Commerce Platform

## Overview
This project is a **Spring Boot-based multi-tenant e-commerce application** that enables multiple stores (tenants) to operate within a single system. Customers can browse products and place orders while administrators manage products and order processing.

## Features
✅ **Multi-Tenancy** – Schema-based database partitioning to support multiple stores.
✅ **User Authentication** – Implements **JWT-based authentication** with Spring Security.
✅ **Product Management** – CRUD operations for product management (Admin access only).
✅ **Order Processing** – Customers can place orders and simulate payments.

## Technologies Used
- **Spring Boot**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA** (MySQL)
- **Hibernate** (Schema-based Multi-Tenancy)
- **Maven** (Dependency Management)
- **Postman** (API Testing)
- **Lombok** (Code Simplification)

---

## Installation and Setup

### Prerequisites
1. **Java 8+** installed
2. **MySQL** installed and running
3. **Postman** for API testing (optional)

### Clone the Repository
```sh
 git clone <your-repo-url>
 cd multi-tenant-ecommerce
```

### Configure Database
Modify the `application.properties` file with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/order-processing-system
spring.datasource.username=root
spring.datasource.password=Priya@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Build and Run the Application
```sh
mvn clean install
mvn spring-boot:run
```

---

## API Endpoints
### **Authentication APIs**
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive a JWT token |

### **Admin Product Management APIs**
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/admin/products/add` | Add a new product (Admin) |
| GET | `/api/admin/products/list` | List all products (Admin) |
| PUT | `/api/admin/products/update/{id}` | Update a product by ID (Admin) |
| DELETE | `/api/admin/products/delete/{id}` | Delete a product by ID (Admin) |

### **Order Management APIs**
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/orders` | Place an order |

---

## Multi-Tenancy Implementation
- Uses **schema-based partitioning** where each store (tenant) has a separate schema in the database.
- Hibernate dynamically selects the schema based on the tenant.
- **TenantResolver** is used to determine the current tenant from the request.

---

## Security Implementation
- **JWT (JSON Web Token)** is used for authentication.
- Users must provide a valid JWT token in the **Authorization** header for secured endpoints.

---

## Testing the APIs
1. **Register a user:** Send a `POST` request to `/api/auth/register` with:
```json
{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}
```
2. **Login to get JWT Token:** Send a `POST` request to `/api/auth/login` and use the token for further requests.
3. **Add a product (Admin only):** Use the JWT token in the `Authorization` header to send a `POST` request to `/api/admin/products/add`.

---

## Future Enhancements
- Add support for **role-based access control (RBAC)**.
- Implement **payment gateway integration**.
- Introduce **inventory management**.

---

## Author
**Priya**  
Java Developer | Spring Boot | MySQL | Full Stack Developer



