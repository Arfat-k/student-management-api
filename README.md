# 🎓 Student Management REST API

A fully secured, production-ready REST API built with Spring Boot, featuring JWT authentication, role-based access control, Redis caching, and Docker deployment.
![CI/CD](https://github.com/Arfat-k/student-management-api/actions/workflows/ci-cd.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Redis](https://img.shields.io/badge/Redis-7-red)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

---

## 📌 Features

- ✅ Full CRUD for student records
- ✅ JWT-based authentication (login & register)
- ✅ Role-based access control (ADMIN, STUDENT)
- ✅ Input validation & global error handling
- ✅ Redis caching for improved performance
- ✅ Dockerized with Docker Compose
- ✅ 6 unit tests with JUnit 5 & Mockito

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| Java 17 | Programming language |
| Spring Boot 3.5.14 | Application framework |
| Spring Security + JWT | Authentication & authorization |
| Spring Data JPA + Hibernate | Database ORM |
| PostgreSQL 16 | Relational database |
| Redis 7 | Caching layer |
| Docker + Docker Compose | Containerization |
| JUnit 5 + Mockito | Unit testing |
| Lombok | Boilerplate reduction |
| Maven | Build tool |

---

## 📁 Project Structure

```
src/main/java/com/example/student_management/
├── config/
│   ├── RedisConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   └── StudentController.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Student.java
│   └── User.java
├── repository/
│   ├── StudentRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtAuthFilter.java
│   └── JwtUtil.java
└── service/
    └── StudentService.java
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17
- Docker Desktop
- Postman (for testing)

### Run with Docker (Recommended)

```bash
# Clone the repository
git clone https://github.com/Arfat-k/student-management-api.git
cd student-management-api

# Build the JAR
.\mvnw.cmd clean package -DskipTests

# Start all containers
docker-compose up --build
```

App will be running at: `http://localhost:8080`

### Run Locally (without Docker)

1. Make sure PostgreSQL is running on port `5432`
2. Create database: `student_db`
3. Update `application.properties` with your DB password
4. Run the app from IntelliJ or:

```bash
.\mvnw.cmd spring-boot:run
```

---

## 🔐 API Endpoints

### Auth Endpoints (Public)

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login & get JWT token |

### Student Endpoints (Protected - requires JWT)

| Method | URL | Description | Role |
|--------|-----|-------------|------|
| GET | `/api/students` | Get all students | ADMIN, STUDENT |
| GET | `/api/students/{id}` | Get student by ID | ADMIN, STUDENT |
| POST | `/api/students` | Create new student | ADMIN |
| PUT | `/api/students/{id}` | Update student | ADMIN |
| DELETE | `/api/students/{id}` | Delete student | ADMIN |

---

## 📬 How to Test with Postman

Import the included `postman-collection.json` file into Postman.

### Step 1 — Register:
```json
POST /api/auth/register
{
    "username": "admin",
    "password": "admin123",
    "role": "ADMIN"
}
```

### Step 2 — Login:
```json
POST /api/auth/login
{
    "username": "admin",
    "password": "admin123"
}
```
Copy the returned JWT token.

### Step 3 — Use Token:
In Postman → Authorization → Bearer Token → paste token

### Step 4 — Create Student:
```json
POST /api/students
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "1234567890",
    "course": "Computer Science"
}
```

---

## ✅ Running Tests

```bash
.\mvnw.cmd test
```

6 unit tests covering:
- Get all students
- Get student by ID
- Get student by ID (not found)
- Create student
- Create student (duplicate email)
- Delete student

---

## 🐳 Docker Services

| Service | Image | Port |
|---------|-------|------|
| Spring Boot App | Custom build | 8080 |
| PostgreSQL | postgres:16 | 5432 |
| Redis | redis:7-alpine | 6379 |

---

## ⚙️ Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SPRING_DATASOURCE_URL` | jdbc:postgresql://postgres:5432/student_db | Database URL |
| `SPRING_DATASOURCE_USERNAME` | postgres | DB username |
| `SPRING_DATASOURCE_PASSWORD` | - | DB password |
| `SPRING_REDIS_HOST` | redis | Redis host |
| `SPRING_REDIS_PORT` | 6379 | Redis port |

---

## 👨‍💻 Author

**Arfat-k**
- GitHub: [@Arfat-k](https://github.com/Arfat-k)
- Repository: [student-management-api](https://github.com/Arfat-k/student-management-api)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
