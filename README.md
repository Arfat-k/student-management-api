# 🎓 Student Management REST API

A fully secured, production-ready REST API built with Spring Boot, featuring JWT authentication, role-based access control, Redis caching, Docker deployment, Kubernetes orchestration, CI/CD pipeline, and AWS deployment with Terraform.

![CI/CD](https://github.com/Arfat-k/student-management-api/actions/workflows/ci-cd.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Redis](https://img.shields.io/badge/Redis-7-red)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![Kubernetes](https://img.shields.io/badge/Kubernetes-K8s-326CE5)
![Terraform](https://img.shields.io/badge/Terraform-IaC-7B42BC)
![AWS](https://img.shields.io/badge/AWS-EC2-FF9900)

---

## 🌍 Live API
```
http://18.204.23.153:8080
```

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
- ✅ AWS EC2 deployment with Terraform
- ✅ Infrastructure as Code (IaC)
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
| Terraform | Infrastructure as Code |
| AWS EC2 | Cloud deployment |
| JUnit 5 + Mockito | Unit testing |
| Lombok | Boilerplate reduction |
| Maven | Build tool |

---

## 📁 Project Structure

```
student-management/
├── .github/
│   └── workflows/
│       └── ci-cd.yml                    # GitHub Actions CI/CD pipeline
├── terraform/
│   ├── main.tf                          # AWS EC2 + Security Group
│   ├── variables.tf                     # Reusable variables
│   └── outputs.tf                       # Output EC2 public IP
├── src/
│   ├── main/java/com/example/student_management/
│   │   ├── config/
│   │   │   ├── RedisConfig.java         # Redis cache configuration
│   │   │   └── SecurityConfig.java      # JWT security configuration
│   │   ├── controller/
│   │   │   ├── AuthController.java      # Register & Login endpoints
│   │   │   └── StudentController.java   # CRUD endpoints
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java  # Global error handling
│   │   ├── model/
│   │   │   ├── Student.java             # Student entity
│   │   │   └── User.java               # User entity
│   │   ├── repository/
│   │   │   ├── StudentRepository.java   # Student DB operations
│   │   │   └── UserRepository.java      # User DB operations
│   │   ├── security/
│   │   │   ├── JwtAuthFilter.java       # JWT request interceptor
│   │   │   └── JwtUtil.java            # JWT generate & validate
│   │   └── service/
│   │       └── StudentService.java      # Business logic + caching
│   └── test/
│       └── StudentServiceTest.java      # Unit tests
├── Dockerfile                           # Docker image definition
├── docker-compose.yml                   # Multi-container setup
├── postman-collection.json              # API test collection
└── pom.xml                              # Maven dependencies
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17
- Docker Desktop
- Postman (for API testing)
- Terraform (for AWS deployment)
- AWS CLI (for cloud deployment)

---

### Option 1 — Run with Docker Compose (Recommended for Local)

```bash
# Clone the repository
git clone https://github.com/Arfat-k/student-management-api.git
cd student-management-api

# Build the JAR file
.\mvnw.cmd clean package -DskipTests

# Start all containers (App + PostgreSQL + Redis)
docker-compose up --build
```

App runs at: `http://localhost:8080`

---

### Option 2 — Deploy to AWS with Terraform

```bash
# Configure AWS credentials
aws configure

# Navigate to terraform folder
cd terraform

# Initialize Terraform
terraform init

# Preview what will be created
terraform plan

# Create AWS infrastructure
terraform apply

# App will be live at the output IP on port 8080

# When done, destroy to avoid charges
terraform destroy
```

---

### Option 3 — Run with Kubernetes

```bash
# Deploy all services
kubectl apply -f k8s/postgres-deployment.yaml
kubectl apply -f k8s/redis-deployment.yaml
kubectl apply -f k8s/app-deployment.yaml

# Check all pods are running
kubectl get pods

# Forward port for local access
kubectl port-forward service/student-app 8080:8080
```

App runs at: `http://localhost:8080`

---

### Option 4 — Run Locally (without Docker)

1. Install PostgreSQL and create database: `student_db`
2. Update `src/main/resources/application.properties` with your DB password
3. Run:

```bash
.\mvnw.cmd spring-boot:run
```

---

## 🔐 API Endpoints

### Auth Endpoints (No authentication required)

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |

### Student Endpoints (JWT token required)

| Method | URL | Description | Role Required |
|--------|-----|-------------|--------------|
| GET | `/api/students` | Get all students | ADMIN, STUDENT |
| GET | `/api/students/{id}` | Get student by ID | ADMIN, STUDENT |
| POST | `/api/students` | Create new student | ADMIN |
| PUT | `/api/students/{id}` | Update student | ADMIN |
| DELETE | `/api/students/{id}` | Delete student | ADMIN |

---

## 📬 Testing with Postman

Import `postman-collection.json` into Postman for ready-made requests.

### Step 1 — Register a user
```
POST http://localhost:8080/api/auth/register
Body:
{
    "username": "admin",
    "password": "admin123",
    "role": "ADMIN"
}
```

### Step 2 — Login to get JWT token
```
POST http://localhost:8080/api/auth/login
Body:
{
    "username": "admin",
    "password": "admin123"
}
Response:
{
    "token": "eyJhbGci...",
    "role": "ADMIN"
}
```

### Step 3 — Use the token
```
In Postman → Authorization tab → Bearer Token → paste token
```

### Step 4 — Create a student
```
POST http://localhost:8080/api/students
Body:
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

Every push to the `main` branch automatically triggers:

```
Developer pushes code
        ↓
GitHub Actions triggered
        ↓
1. Build project with Maven
2. Run unit tests
3. Build Docker image
4. Push image to Docker Hub
```

Docker Hub: [arfatk/student-management-api](https://hub.docker.com/r/arfatk/student-management-api)

---

## ☁️ Terraform — Infrastructure as Code

Instead of manually clicking in AWS Console, Terraform creates everything automatically:

```bash
terraform apply    # Creates EC2 + Security Group + installs Docker + runs app
terraform destroy  # Deletes everything when done
```

### What Terraform creates:
- AWS Security Group (opens ports 22, 8080)
- EC2 t3.micro instance
- Installs Docker & Docker Compose automatically
- Pulls Docker image and starts all containers

---

## ☸️ Kubernetes Commands

```bash
# Deploy all services
kubectl apply -f k8s/

# Check running pods
kubectl get pods

# Check services
kubectl get services

# View application logs
kubectl logs deployment/student-app

# Scale to 3 instances
kubectl scale deployment student-app --replicas=3

# Forward port for local access
kubectl port-forward service/student-app 8080:8080

# Delete all deployments
kubectl delete -f k8s/
```

---

## ✅ Running Tests

```bash
.\mvnw.cmd test
```

### Test Coverage:

| Test | Description |
|------|-------------|
| getAllStudents | Returns list of all students |
| getStudentById | Returns student by ID |
| getStudentById_notFound | Throws exception for invalid ID |
| createStudent | Saves and returns new student |
| createStudent_emailExists | Throws exception for duplicate email |
| deleteStudent | Deletes student successfully |

---

## 🐳 Docker Services

| Service | Image | Port |
|---------|-------|------|
| Spring Boot App | arfatk/student-management-api | 8080 |
| PostgreSQL | postgres:16 | 5432 |
| Redis | redis:7-alpine | 6379 |

---

## ⚙️ Environment Variables

| Variable | Description |
|----------|-------------|
| `SPRING_DATASOURCE_URL` | PostgreSQL connection URL |
| `SPRING_DATASOURCE_USERNAME` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `SPRING_DATA_REDIS_HOST` | Redis hostname |
| `SPRING_DATA_REDIS_PORT` | Redis port (default: 6379) |
| `JWT_SECRET` | Secret key for JWT signing |
| `JWT_EXPIRATION` | Token expiry in milliseconds |

---

## 🏗️ Architecture

```
Client (Postman/Browser)
        ↓
Spring Boot REST API (Port 8080)
        ↓
Spring Security → JWT Filter → Controller
        ↓
Service Layer (Business Logic + Redis Cache)
        ↓
Repository Layer (Spring Data JPA)
        ↓
PostgreSQL Database
```

---

## 👨‍💻 Author

**Arfat-k**
- GitHub: [@Arfat-k](https://github.com/Arfat-k)
- Docker Hub: [arfatk](https://hub.docker.com/r/arfatk/student-management-api)
- Repository: [student-management-api](https://github.com/Arfat-k/student-management-api)
- Live API: http://18.204.23.153:8080

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).