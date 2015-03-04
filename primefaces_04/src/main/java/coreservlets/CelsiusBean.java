package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="cBean")
public class CelsiusBean {
  private double c;

  public double getC() {
    return(c);
  }

  public void setC(double c) {
    this.c = Math.max(c, -273.15); // -273.15 is absolute zero
  }
  
  public double getF() {
    return(c*9.0/5.0 + 32);
  }
}
