package coreservlets;

import java.io.Serializable;
import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class BankingBean3 extends BankingBean implements Serializable {
  @Override
  public String showBalance() {
    String origResult = super.showBalance();
    origResult = origResult.replace("bankingBean", "bankingBean3");
    return(origResult + "3?faces-redirect=true");
  }
}
