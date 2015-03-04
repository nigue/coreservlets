package coreservlets;

import java.util.Date;
import javax.faces.bean.*;

@ManagedBean
public class TestBean {
  private double number;
  private Date date;
  
  public double getNumber() {
    return(number);
  }

  public void setNumber(double number) {
    this.number = number;
  }

  public Date getDate() {
    return(date);
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String doAction() {
    return("show-test-data");
  }
}
