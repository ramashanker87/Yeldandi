package com.rama.app.controller;

import com.rama.app.controller.EmployeeController;
import com.rama.app.model.Employee;
import com.rama.app.service.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
  @InjectMocks
  EmployeeController employeController;
  @Mock
  EmployeeService employeeService;

  @Test
  public void testGetAllEmployees() {
    Map<String, Employee> employeeMap = new HashMap<>();
    Employee employee1 = new Employee("1","Emp1",25);
    Employee employee2 = new Employee("2","Emp2",26);
    Employee employee3 = new Employee("3","Emp3",27);
    employeeMap.put(employee1.getId(), employee1);
    employeeMap.put(employee2.getId(), employee2);
    employeeMap.put(employee3.getId(), employee3);
    when(employeeService.readAllEmployee()).thenReturn(employeeMap);
    Map<String, Employee> employeeResultMap=employeController.getAllEmployees();
    assert employeeResultMap!=null;
    assert employeeResultMap.size()==3;
    assert employeeResultMap.get(employee1.getId())==employee1;
    assert employeeResultMap.get(employee2.getId())==employee2;
    assert employeeResultMap.get(employee3.getId())==employee3;
  }

  @Test
  public void testCreateEmployees() {
    Employee employee1 = new Employee("1","Emp1",25);
    when(employeeService.createEmployee(employee1)).thenReturn(employee1);
    Employee resultEmployee=employeController.createEmployees(employee1);
    assert resultEmployee!=null;
    assert resultEmployee.getId()==employee1.getId();
    assert resultEmployee.getName().equals("Emp1");
    assert resultEmployee.getAge()==25;
  }
  @Test
  public void testUpdateEmployees() {
    Employee employee1 = new Employee("1","Emp1",25);
    Employee employee2 = new Employee("2","Emp2",26);
    when(employeeService.updateEmployee(anyString(),anyInt())).thenReturn(employee2);
    Employee resultEmployee=employeController.updateEmployee(employee1.getId(),26);
    assert resultEmployee!=null;
    assert resultEmployee.getId()==employee2.getId();
    assert resultEmployee.getName().equals("Emp2");
    assert resultEmployee.getAge()==26;
  }
  @Test
  public void testDeleteEmployees() {
    doNothing().when(employeeService).deleteEmployee(anyString());
    employeController.deleteEmployee("1");
    verify( employeeService, atLeast(1)).deleteEmployee(anyString());
  }
  @Test
  public void testGetEmployeeByName() {
    employeController.getAllEmployeeByName();
  }

}
