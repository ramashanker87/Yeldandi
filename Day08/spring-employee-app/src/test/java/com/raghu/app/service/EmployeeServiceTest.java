package com.rama.app.service;

import com.rama.app.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
  @InjectMocks
  EmployeeService employeeService;

  @Test
  public void testCreateEmployee() {
    Employee employee = new Employee("1","Emp1",25);
    Employee expectedEmployee=employeeService.createEmployee(employee);
    assert expectedEmployee != null;
    assert expectedEmployee.getId()== employee.getId();
    assert expectedEmployee.getName()== employee.getName();
    assert expectedEmployee.getAge()== employee.getAge();
  }

  @Test
  public void testUpdateEmployee() {
    Employee employee1 = new Employee("1","Emp1",25);
    Employee expectedEmployee=employeeService.createEmployee(employee1);
    Employee resultEmployee=employeeService.updateEmployee(employee1.getId(),30);
    assert resultEmployee != null;
    assert resultEmployee.getId()== employee1.getId();
    assert resultEmployee.getName()== employee1.getName();
    assert resultEmployee.getAge()== 30;
  }

  @Test
  public void testDeleteEmployee() {
    Employee employee1 = new Employee("1","Emp1",25);
    Employee expectedEmployee=employeeService.createEmployee(employee1);
    employeeService.deleteEmployee(employee1.getName());
    Employee expectedResult=employeeService.readEmployeeByName(employee1.getName());
    assert expectedResult== null;

  }
  @Test
  public void testReadEmployeesBYName() {
    Employee employee1 = new Employee("1","Emp1",25);
    Employee actualEmployee=employeeService.createEmployee(employee1);
    Employee expectedResult=employeeService.readEmployeeByName(employee1.getId());
    assert expectedResult != null;
    assert expectedResult.getId()== actualEmployee.getId();
    assert expectedResult.getName()== actualEmployee.getName();
    assert expectedResult.getAge()== actualEmployee.getAge();
  }
  @Test
  public void testReadAllEmployees() {
    Employee employee1 = new Employee("1","Emp1",25);
    Employee employee2 = new Employee("2","Emp2",26);
    Employee employee3 = new Employee("3","Emp3",27);
    employeeService.createEmployee(employee1);
    employeeService.createEmployee(employee2);
    employeeService.createEmployee(employee3);
    Map<String,Employee> resultEmployees= employeeService.readAllEmployee();
    assert resultEmployees.size()==3;
    assert resultEmployees.containsKey(employee1.getId());
    assert resultEmployees.containsKey(employee2.getId());
    assert resultEmployees.containsKey(employee3.getId());
  }
}
