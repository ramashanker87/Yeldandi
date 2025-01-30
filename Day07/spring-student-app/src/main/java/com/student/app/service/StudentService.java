package com.student.app.service;

import com.student.app.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    Map<String, Student> students = new HashMap<>();

    public Map<String, Student> createStudent(Student student) {
        students.put(student.getName(), student);
        return students;
    }

    public Student readStudent(String name) {
        return students.get(name);
    }

    public Student updateStudent(String name, int age) {
        Student student = students.get(name);
        student.setAge(age);
        students.put(student.getName(), student);
        return student;
    }

    public Student deleteStudent(String name) {
        return students.remove(name);
    }
}
