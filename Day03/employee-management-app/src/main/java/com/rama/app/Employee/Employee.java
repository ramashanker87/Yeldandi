package com.rama.app.Employee;
public class Employee {
    String name;
    int age;
    String company;
    String id;
    int salary;

    public Employee(String name, int age, String company, String id, int salary) {
        this.name = name;
        this.age = age;
        this.company = company;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCompany() {
        return company;
    }

    public String getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", company='" + company + "', id='" + id + "', salary=" + salary + '}';
    }
}

import java.util.*;
        import java.util.stream.Collectors;

public class EmployeeManagementApp {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 28, "TechCorp", "E001", 55000));
        employees.add(new Employee("Bob", 32, "InnoTech", "E002", 45000));
        employees.add(new Employee("Charlie", 25, "WebSoft", "E003", 60000));
        employees.add(new Employee("David", 45, "TechCorp", "E004", 35000));
        employees.add(new Employee("Eve", 29, "InnoTech", "E005", 70000));

        List<Employee> filteredList = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .filter(e -> e.getAge() < 30)
                .collect(Collectors.toList());

        System.out.println("Filtered List of Employees (Salary > 50000 and Age < 30):");
        filteredList.forEach(System.out::println);

        Map<String, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getName, e -> e));

        Map<String, Employee> filteredMap = employeeMap.entrySet().stream()
                .filter(entry -> entry.getValue().getSalary() < 40000)
                .filter(entry -> entry.getValue().getAge() > 25)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("\nFiltered Map of Employees (Salary < 40000 and Age > 25):");
        filteredMap.forEach((name, employee) -> System.out.println(name + ": " + employee));
    }
}



