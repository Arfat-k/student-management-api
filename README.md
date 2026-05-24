# 🎓 Student Management REST API

A fully secured, production-ready REST API built with Spring Boot, featuring JWT authentication, role-based access control, Redis caching, Docker deployment, Kubernetes orchestration, and CI/CD pipeline.

![CI/CD](https://github.com/Arfat-k/student-management-api/actions/workflows/ci-cd.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Redis](https://img.shields.io/badge/Redis-7-red)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![Kubernetes](https://img.shields.io/badge/Kubernetes-K8s-326CE5)

---

## 📌 Features

- ✅ Full CRUD for student records
- ✅ JWT-based authentication (login & register)
- ✅ Role-based access control (ADMIN, STUDENT)
- ✅ Input validation & global error handling
- ✅ Redis caching for improved performance
- ✅ Dockerized with Docker Compose
- ✅ Kubernetes deployment (K8s)
- ✅ CI/CD pipeline with GitHub Actions
- ✅ Docker image published to Docker Hub
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
| Kubernetes (K8s) | Container orchestration |
| GitHub Actions | CI/CD pipeline |
| Docker Hub | Container registry |
| JUnit 5 + Mockito | Unit testing |
| Lombok | Boilerplate reduction |
| Maven | Build tool |

---

## 📁 Project Structure

```
student-management/
├── .github/
│   └── workflows/
│       └── ci-cd.yml           # GitHub Actions pipeline
├── k8s/
│   ├── app-deployment.yaml     # K8s app deployment
│   ├── postgres-deployment.yaml # K8s postgres deployment
│   └── redis-deployment.yaml   # K8s redis deployment
├── src/main/java/com/example/student_management/
│   ├── config/
│   │   ├── RedisConfig.java
│   │   └── SecurityConfig.java
│   ├── controller/
│   │   ├── AuthController.java
│   │   └── StudentController.java
│   ├── exception/
│   │   └── GlobalExceptionHandler.java
│   ├── model/
│   │   ├── Student.java
│   │   └── User.java
│   ├── repository/
│   │   ├── StudentRepository.java
│   │   └── UserRepository.java
│   ├── security/
│   │   ├── JwtAuthFilter.java
│   │   └── JwtUtil.java
│   └── service/
│       └── StudentService.java
├── Dockerfile
├── docker-compose.yml
├── postman-collection.json
└── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17
- Docker Desktop
- Postman (for testing)

### Option 1 — Run with Docker Compose (Recommended)

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

---

### Option 2 — Run with Kubernetes

```bash
# Apply all K8s files
kubectl apply -f k8s/postgres-deployment.yaml
kubectl apply -f k8s/redis-deployment.yaml
kubectl apply -f k8s/app-deployment.yaml

# Check pods are running
kubectl get pods

# Forward port to access app
kubectl port-forward service/student-app 8080:8080
```

App will be running at: `http://localhost:8080`

---

### Option 3 — Run Locally

1. Make sure PostgreSQL is running on port `5432`
2. Create database: `student_db`
3. Update `application.properties` with your DB password
4. Run:

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
POST/api/auth/register
{
    "username": "admin",
    "password": "admin123",
    "role": "ADMIN"
}
```

### Step 2 — Login:
```json
POST/api/auth/login
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
POST/api/students
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "1234567890",
    "course": "Computer Science"
}
```

---

## 🔄 CI/CD Pipeline

Every push to `main` branch automatically:

```
Push to GitHub
      ↓
GitHub Actions triggered
      ↓
✅ Build with Maven
✅ Run Tests
✅ Build Docker Image
✅ Push to Docker Hub
```

Docker Hub: [arfatk/student-management-api](https://hub.docker.com/r/arfatk/student-management-api)

---

## ☸️ Kubernetes Commands

```bash
# Deploy everything
kubectl apply -f k8s/

# Check status
kubectl get pods
kubectl get services

# View logs
kubectl logs deployment/student-app

# Scale app to 3 instances
kubectl scale deployment student-app --replicas=3

# Forward port for local access
kubectl port-forward service/student-app 8080:8080

# Delete everything
kubectl delete -f k8s/
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
| Spring Boot App | arfatk/student-management-api | 8080 |
| PostgreSQL | postgres:16 | 5432 |
| Redis | redis:7-alpine | 6379 |

---

## ☸️ Kubernetes Resources

| Resource | Type | Replicas |
|----------|------|---------|
| student-app | Deployment | 1 (scalable) |
| postgres | Deployment | 1 |
| redis | Deployment | 1 |
| student-app | NodePort Service | port 30080 |

---

## ⚙️ Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SPRING_DATASOURCE_URL` | jdbc:postgresql://postgres:5432/student_db | Database URL |
| `SPRING_DATASOURCE_USERNAME` | postgres | DB username |
| `SPRING_DATASOURCE_PASSWORD` | - | DB password |
| `SPRING_DATA_REDIS_HOST` | redis | Redis host |
| `SPRING_DATA_REDIS_PORT` | 6379 | Redis port |

---

## 👨‍💻 Author

**Arfat-k**
- GitHub: [@Arfat-k](https://github.com/Arfat-k)
- Docker Hub: [arfatk](https://hub.docker.com/r/arfatk/student-management-api)
- Repository: [student-management-api](https://github.com/Arfat-k/student-management-api)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).