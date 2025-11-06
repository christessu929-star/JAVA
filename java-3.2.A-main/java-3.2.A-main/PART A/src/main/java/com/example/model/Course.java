package com.example.model;

public class Course {
    private String courseName;

    public Course() {
        this.courseName = "Spring Framework";
    }

    public String getCourseName() {
        return courseName;
    }

    public void displayCourse() {
        System.out.println("Enrolled in course: " + courseName);
    }
}
