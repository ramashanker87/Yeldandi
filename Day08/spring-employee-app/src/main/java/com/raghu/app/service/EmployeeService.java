package com.rama.app.service;

import com.rama.app.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
  Map<String, Employee> employees = new HashMap<String, Employee>();

  public Employee createEmployee(Employee employee) {
    employees.put(employee.getId(),employee);
    return employee;
  }

  public Employee updateEmployee(String id, int newAge) {
    Employee employee1= employees.get(id);
    employee1.setAge(newAge);
    return employee1;
  }

  public void deleteEmployee(String id) {
    employees.remove(id);
  }

  public Employee readEmployeeByName(String name) {
    Employee result=employees.get(name);
    return result;
  }
  public Map<String,Employee> readAllEmployee() {
    return employees;
  }
}
