package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NumberGenerator  {
  private double number = Math.random();
  private double range = 1.0;
  
  public double getRange() {
    return(range);
  }

  public void setRange(double range) {
    this.range = range;
  }

  public double getNumber() {
    return(number);
  }
  
  public double getNumber2() {
    return(Math.random() * range);
  }
  
  // If this is used only with f:ajax, then it is legal to declare it 
  // public void randomize(). However, better to have it return null
  // so that it can be used in non-Ajax apps as well.
  
  public String randomize() {
    number = range * Math.random();
    return(null);
  }
}
