package com.lms.model;

public abstract class Person {
  protected int id;
  protected String name;

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  // Abstraction: subclasses MUST define this
  public abstract String getRole();
}