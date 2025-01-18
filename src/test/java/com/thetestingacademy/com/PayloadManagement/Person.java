package com.thetestingacademy.com.PayloadManagement;
// An example of the POJO class
public class Person {
    // In order to make POJO class, make the instance variables as private
      private String name;
      private Integer age;
  // Generate a default constructor
    public Person() {

    }

 // Generate getter and setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
