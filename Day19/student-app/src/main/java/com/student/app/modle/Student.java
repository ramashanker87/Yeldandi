package com.student.app.modle;

import org.springframework.stereotype.Component;

@Component
public class Student {
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private int id;
  private String name;
}
