package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Winners {
  public String getWinner1() {
    System.out.println("Called getWinner1");
    return("Jane");
  }
  
  public String getWinner3() {
    return("Juan");
  }
}
