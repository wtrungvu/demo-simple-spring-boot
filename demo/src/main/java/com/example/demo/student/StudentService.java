package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
        }
        studentRepository.save(student);
        System.out.println("Student with email " + student.getEmail() + " registered successfully");
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
        System.out.println("Student with id " + studentId + " deleted successfully");
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + studentId + " does not exist"));

        if (name != null &&
                !name.isEmpty() &&
                name.length() > 0 &&
                !name.equals(student.getName())) {
            student.setName(name);
        }

        if (email != null &&
                !email.isEmpty() &&
                email.length() > 0 &&
                !email.equals(student.getEmail())) {
            student.setEmail(email);
        }

        studentRepository.save(student);
        System.out.println("Student with id " + studentId + " updated successfully");
    }
}
