package com.student.app.controller;

import com.student.app.model.Student;
import com.student.app.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/post")
    public Map<String, Student> postStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @GetMapping("/get")
    public Student getStudent(@RequestParam String name) {
        return studentService.readStudent(name);
    }
    @PutMapping("/put")
    public Student putStudent(@RequestParam String name, @RequestParam int age) {
        return studentService.updateStudent(name,age);
    }
    @DeleteMapping("/delete")
    public Student deleteStudent(@RequestParam String name) {
        return studentService.deleteStudent(name);
    }
}
