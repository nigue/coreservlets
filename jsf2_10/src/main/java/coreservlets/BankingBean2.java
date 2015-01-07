package coreservlets;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class BankingBean2 extends BankingBean implements Serializable {
  @Override
  public String showBalance() {
    String origResult = super.showBalance();
    origResult = origResult.replace("bankingBean", "bankingBean2");
    return(origResult + "2");
  }
}
