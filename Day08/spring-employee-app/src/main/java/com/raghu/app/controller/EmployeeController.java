package com.rama.app.controller;

import com.rama.app.model.Employee;
import com.rama.app.service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
 private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/get/all/employee")
  public Map<String, Employee> getAllEmployees() {
    return employeeService.readAllEmployee();
  }
  @GetMapping("/get/employee")
  public void getAllEmployeeByName() {
  }

  @PostMapping("/create/employee")
  public Employee createEmployees(@RequestBody Employee employee) {
    return employeeService.createEmployee(employee);
  }

  @PutMapping("/update/employee")
  public Employee updateEmployee(@RequestParam("id") String id,@RequestParam("age") int age) {
    return employeeService.updateEmployee(id,age);
  }

  @DeleteMapping("/delete/employee")
  public void deleteEmployee(@RequestParam("id") String id) {
   employeeService.deleteEmployee(id);
  }
}
