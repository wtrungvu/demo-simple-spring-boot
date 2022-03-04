package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            Student john = new Student(
                    1L,
                    "John",
                    "John123@gmail.com",
                    LocalDate.of(1990, JANUARY, 1)
            );

            Student william = new Student(
                    2L,
                    "william",
                    "william@gmail.com",
                    LocalDate.of(2004, JANUARY, 5)
            );

            studentRepository.saveAll(
                    List.of(john, william)
            );
        };
    }

}
