package com.example.student_management;

import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@example.com");
        student.setPhone("1234567890");
        student.setCourse("Computer Science");
    }

    @Test
    void getAllStudents_returnsAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        List<Student> result = studentService.getAllStudents();
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById_returnsStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById(1L);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void getStudentById_throwsException_whenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> studentService.getStudentById(99L));
    }

    @Test
    void createStudent_savesAndReturnsStudent() {
        when(studentRepository.existsByEmail(student.getEmail())).thenReturn(false);
        when(studentRepository.save(student)).thenReturn(student);
        Student result = studentService.createStudent(student);
        assertEquals("john@example.com", result.getEmail());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void createStudent_throwsException_whenEmailExists() {
        when(studentRepository.existsByEmail(student.getEmail())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> studentService.createStudent(student));
    }

    @Test
    void deleteStudent_deletesSuccessfully() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}