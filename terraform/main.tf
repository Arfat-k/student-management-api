provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "student_app_sg" {
  name        = "student-app-sg"
  description = "Security group for student management app"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "student_app" {
  ami           = "ami-0c7217cdde317cfec"
  instance_type = "t3.micro"
  key_name      = "student-key"

  vpc_security_group_ids = [aws_security_group.student_app_sg.id]

  user_data = <<-EOF
    #!/bin/bash
    apt-get update
    apt-get install -y docker.io docker-compose
    systemctl start docker
    systemctl enable docker
    usermod -aG docker ubuntu
    cat > /home/ubuntu/docker-compose.yml << 'COMPOSE'
    version: '3.8'
    services:
      postgres:
        image: postgres:16
        environment:
          POSTGRES_DB: student_db
          POSTGRES_USER: postgres
          POSTGRES_PASSWORD: Arfat@5291
        ports:
          - "5432:5432"
      redis:
        image: redis:7-alpine
        ports:
          - "6379:6379"
      app:
        image: arfatk/student-management-api:latest
        ports:
          - "8080:8080"
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/student_db
          SPRING_DATASOURCE_USERNAME: postgres
          SPRING_DATASOURCE_PASSWORD: Arfat@5291
          SPRING_DATA_REDIS_HOST: redis
          SPRING_DATA_REDIS_PORT: 6379
        depends_on:
          - postgres
          - redis
    COMPOSE
    cd /home/ubuntu && docker-compose up -d
  EOF

  tags = {
    Name = "student-management-server"
  }
}