# 📝 Feelings Tracker API

A Spring Boot 3 + MySQL REST API for tracking user feelings, secured with JWT and role-based access (User/Admin).

## 🚀 Features

### ✅ Authentication & Authorization
- Register and login with JWT token response
- Role-based access: `USER` and `ADMIN`
- Secure endpoints using Spring Security

### 👤 User Management
- Get authenticated user info
- Update password
- Delete own profile
- Admin-only: Get all users, promote to admin, delete users

### 💭 Feelings CRUD
- Create, get one, get all, delete, and update feelings
- Each feeling is tied to its creator (user-based access control)
- `updatedAt` tracked automatically

## 🧑‍💻 Tech Stack
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA + Hibernate
- MySQL
- Swagger / OpenAPI for API testing
- Maven

## ⚙️ Setup Instructions

1. **Clone the project:**

   ```bash
   git clone https://github.com/NefisaB/feelings-tracker.git
   cd feelings-tracker
   ```

2. **Create MySQL database and user:**

   ```sql
   CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'spring';
   CREATE DATABASE feelingsdb;
   GRANT ALL PRIVILEGES ON feelingsdb.* TO 'springuser'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Configure application properties:**

   Refactor (rename) `application.example.properties` to `application.properties` and fill in your values where needed.

4. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

5. **Visit Swagger UI:**

   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## 📬 API Endpoints

| Method | Endpoint            | Role   | Description               |
|--------|---------------------|--------|---------------------------|
| POST   | /api/auth/register  | Public | Register new user         |
| POST   | /api/auth/login     | Public | Login and receive JWT     |
| GET    | /api/users/info     | USER   | Get own info              |
| PUT    | /api/users/password | USER   | Change password           |
| DELETE | /api/users          | USER   | Delete own account        |
| GET    | /api/admin/users    | ADMIN  | Get all users             |
| PUT    | /api/admin/{id}     | ADMIN  | Promote user to ADMIN     |
| DELETE | /api/admin/{id}     | ADMIN  | Delete any non-admin user |
| GET    | /api/feelings       | USER   | Get all own feelings      |
| GET    | /api/feelings/{id}  | USER   | Get specific feeling      |
| POST   | /api/feelings       | USER   | Create a new feeling      |
| PUT    | /api/feelings/{id}  | USER   | Update a feeling          |
| DELETE | /api/feelings/{id}  | USER   | Delete a feeling          |

## 🧪 Testing

Use Swagger or Postman to test endpoints with JWT.  
**Header:**

```http
Authorization: Bearer <your-token>
```

Happy coding! 🎉
   



