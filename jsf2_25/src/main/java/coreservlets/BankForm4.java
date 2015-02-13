package coreservlets;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
public class BankForm4 extends BankForm1 {
  @Override
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
    customer = lookupService.findCustomer(customerId);
  }
  
  @Override
  public String findBalance() {
    FacesContext context = 
      FacesContext.getCurrentInstance();
    if (customer == null) {
      String message =
        String.format("Unknown ID '%s'", customerId);
      context.addMessage("customerId", new FacesMessage(message));
    } 
    if (context.getMessageList().size() > 0) {
      return(null);
    } else {
      String result = 
        "/jsf/result/show-customer4?faces-redirect=true" +
        "&amp;includeViewParams=true";  
      return (result);
    }
  }
}
