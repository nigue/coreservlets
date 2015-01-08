package coreservlets;

import coreservlets.base.BankingBeanBase;
import coreservlets.map.CustomerSimpleMap;
import coreservlets.service.CustomerLookupService;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author anibal
 */
@ManagedBean 
public class BankingBeanAjax extends BankingBeanBase { 
  private String message = "";
  
  public String getMessage() {
    return(message);
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  @Override
  public String showBalance() {
    if (!password.equals("secret")) {
      message = "Incorrect password";
    } else {
      CustomerLookupService service =
        new CustomerSimpleMap();
      customer = service.findCustomer(customerId);
      if (customer == null) {
        message = "Unknown customer";
      } else {
        message = 
          String.format("Balance for %s %s is $%,.2f",
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getBalance());
      }
    }
    return(null);
  }
}
