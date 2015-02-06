package coreservlets;

import coreservlets.data.Nameable;

public class Person implements Nameable {
  private String firstName="", lastName="";

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public Person() {}
  
  @Override
  public String getFirstName() {
    return(firstName);
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return(lastName);
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return(firstName + " " + lastName);
  }
}
