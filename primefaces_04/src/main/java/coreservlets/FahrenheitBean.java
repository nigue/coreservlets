package coreservlets;

// Note that the names for this bean (fBean1, fBean2, fBean3) are
// are defined in faces-config.xml. The repeated names are so that I can use
// more than one instance in the same page.

public class FahrenheitBean {
  private int f=32;
  
  public int getF() {
    return(f);
  }

  public void setF(int f) {
    this.f = Math.max(f, -460);  // -459.67 is absolute zero
  }

  public int getC() {
    return((int)((f - 32)*(5.0/9.0)));
  }
  
  public String getStatus() {
    return(String.format("%s&deg;F = %s&deg;C", 
                         f, getC()));
  }
}
