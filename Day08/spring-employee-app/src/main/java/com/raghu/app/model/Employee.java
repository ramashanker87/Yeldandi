package com.rama.app.model;

public class Employee {
  public String getId() {
    return id;
  }



  public String getName() {
    return name;
  }



  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  String id;
  String name;
  int age;
  public Employee() {}
  public Employee(String id, String name, int age) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
  }
  
}
