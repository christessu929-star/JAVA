package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.AppConfig;
import com.example.model.Student;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student = context.getBean(Student.class);
        student.showDetails();
        context.close();
    }
}
