package com.student.app.controller;

import com.student.app.modle.Student;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {
  private Map<String, Student> students = new HashMap<>();

  @GetMapping("/get/student")
  public Student getStudent(@RequestParam String name) {
    return students.get(name);
  }

  @GetMapping("/get/all/student")
  public Map<String, Student> getAllStudent() {
    return students;
  }

  @PostMapping("/save/student")
  public Student saveStudent(@RequestBody Student student) {
    String name= student.getName();
    students.put(name, student);
    return student;
  }

  @PutMapping("/update/student")
  public Student updateStudent(@RequestParam String name,@RequestParam int id) {
    Student student = students.get(name);
    student.setId(id);
    students.put(name, student);
    return student;
  }

  @DeleteMapping
  public void deleteStudent(@RequestParam String name) {
    students.remove(name);
  }

}
