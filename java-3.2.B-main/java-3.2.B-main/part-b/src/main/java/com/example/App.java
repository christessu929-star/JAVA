package com.example;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.util.HibernateUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();

        // Create
        Long id = dao.create(new Student("Aman", 20, "CSE"));
        System.out.println("Created student id: " + id);

        // Read
        Student s = dao.read(id);
        System.out.println("Read: " + s);

        // Update
        s.setAge(21);
        dao.update(s);
        System.out.println("Updated: " + dao.read(id));

        // Read all
        List<Student> all = dao.readAll();
        System.out.println("All students: " + all);

        // Delete
        dao.delete(id);
        System.out.println("Deleted student id: " + id);

        HibernateUtil.shutdown();
    }
}
