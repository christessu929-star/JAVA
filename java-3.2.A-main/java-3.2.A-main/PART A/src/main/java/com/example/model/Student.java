package com.example.model;

public class Student {
    private final Course course;

    public Student(Course course) {
        this.course = course;
    }

    public void showDetails() {
        System.out.println("Student object created successfully!");
        course.displayCourse();
    }
}
