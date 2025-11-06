package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.model.Course;
import com.example.model.Student;

@Configuration
public class AppConfig {

    @Bean
    public Course course() {
        return new Course();
    }

    @Bean
    public Student student() {
        return new Student(course());
    }
}
