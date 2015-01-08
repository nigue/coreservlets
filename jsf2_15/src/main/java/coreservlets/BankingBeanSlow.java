package coreservlets;

import javax.faces.bean.*;

@ManagedBean 
public class BankingBeanSlow extends BankingBeanAjax {
  @Override
  public String showBalance() {
    try { 
      Thread.sleep(5000); 
    } catch(InterruptedException ie) {}
    return(super.showBalance());
  }
}
