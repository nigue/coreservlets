package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CurrencyBean {
  private double dollars=100;
  
  public double getDollars() {
    return(dollars);
  }

  public void setDollars(double dollars) {
    this.dollars = dollars;
  }
  
  /** Dollar to Yen conversion taken from xe.com as of August 2013. */
  
  public double getYen() {
    return(dollars * 97.13);
  }
}
