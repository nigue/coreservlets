package coreservlets;

import javax.faces.bean.*;

@ManagedBean
public class Person {
  private String firstName, lastName, emailAddress;
  
  public String getFirstName() {
    return(firstName);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return(lastName);
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return(emailAddress);
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  
  public String doRegistration() {
    if (isAnyEmpty(firstName, lastName, emailAddress)) {
      return("missing-input");
    } else {
      return("confirm-registration");
    }
  }
  
  private boolean isAnyEmpty(String... values) {
    for(String value: values) {
      if (isEmpty(value)) {
        return(true);
      }
    }
    return(false);
  }
  
  private boolean isEmpty(String value) {
    return((value == null) || (value.trim().length() == 0));
  }
}