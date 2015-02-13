package coreservlets.data;

import javax.faces.bean.*;

@ManagedBean
public class Customer {
  private String id, firstName, lastName;
  private double balance;

  public Customer(String id,
                  String firstName,
                  String lastName,
                  double balance) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.balance = balance;
  }
  
  public Customer() {}
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
  
  public double getBalance() {
    return(balance);
  }
  
  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getBalanceNoSign() {
    String balanceString = 
      String.format("%,.2f", Math.abs(balance));
    return(balanceString);
  }
}
  
