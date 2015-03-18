package coreservlets;

public class TestBean {
  private double number;
  
  public double getNumber() {
    return(number);
  }

  public void setNumber(double number) {
    this.number = number;
  }

  public String doAction() {
    return("show-test-data");
  }
}
