package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int totalNumPerson;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    totalNumPerson++;
  }
  
  public void setAge(int age){
    if(age < 0){
      throw new IllegalArgumentException();
    }
    this.age = age;
  }
  
  public void setName(String name){
    if(name == null){
      throw new IllegalArgumentException();
    }
    this.name = name;
  }
  
  public void setSalary(double salary){
    this.salary = salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public int getAge(){
    return this.age;
  }
  
  public String getName(){
    return this.name;
  }
  
  public double getSalary(){
    return this.salary;
  }
  
  public String getSSN(){
    return this.ssn;
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }
  
  public static int count(){
    return totalNumPerson;
  }
  
  @Override
  public boolean equals(Object other){
    boolean flag = false;
    
    if (!(other instanceof Person)){
      return flag;
    }
    Person p = (Person) other;
    if(this.name.equals(p.name) && this.age == p.age){
      flag = true;
    }
    return flag;
  }
  
     public static class AgeComparator implements Comparator<Person>{
       @Override
       public int compare(Person p1, Person p2){
         if(p1.getAge() > p2.getAge()){
           return 1;
         }else if(p1.getAge() == p2.getAge()){
           return 0;
         }else{
           return -1;
         }
       }
     }
  
  @Override
  public int compareTo(Person other){
    if(this.salary > other.salary){
      return -1;
    }else if(this.salary == other.salary){
      return 0;
    }else{
     return 1;
    }
  }
  
  public static ArrayList<Person> getNewardFamily(){
    ArrayList<Person> list = new ArrayList<Person>();
    Person Ted = new Person("Ted", 41, 250000);
    Person Charlotte = new Person("Charlotte", 43, 150000);
    Person Michael = new Person("Michael", 22, 10000);
    Person Matthew = new Person("Matthew", 15, 0);
    list.add(Ted);
    list.add(Charlotte);
    list.add(Michael);
    list.add(Matthew);
    System.out.println(list.toString());
    return list;
  }

  public static void main(String[] args){
    List<Person> people = Person.getNewardFamily();
    Collections.sort(people, new Person.AgeComparator());
    Person p= new Person("Matthew", 15, 0);
    boolean x=p.equals(people.get(0));
    System.out.println("test");
  }
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}


