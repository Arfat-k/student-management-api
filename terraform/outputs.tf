output "public_ip" {
  description = "Public IP of the EC2 instance"
  value       = aws_instance.student_app.public_ip
}

output "public_dns" {
  description = "Public DNS of the EC2 instance"
  value       = aws_instance.student_app.public_dns
}