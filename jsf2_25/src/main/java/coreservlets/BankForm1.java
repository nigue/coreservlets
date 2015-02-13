package coreservlets;

import coreservlets.data.Customer;
import coreservlets.map.CustomerSimpleMap;
import coreservlets.service.CustomerLookupService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;



@ManagedBean 
public class BankForm1 { 
  protected String customerId, password;
  protected Customer customer = new Customer();
  protected static CustomerLookupService lookupService =
    new CustomerSimpleMap();

  public String getCustomerId() {
    return(customerId);
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
  
  public String getPassword() {
    return (password);
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public Customer getCustomer() {
    return(customer);
  }

  public String findBalance() {
    customer = lookupService.findCustomer(customerId);
    FacesContext context = 
      FacesContext.getCurrentInstance();
    if (customer == null) {
      String message =
        String.format("Unknown ID '%s'", customerId);
      context.addMessage("customerId", new FacesMessage(message));
    } 
    if (!password.equals("secret")) {
      String message = "Incorrect password";
      context.addMessage("password", new FacesMessage(message));
    }
    if (context.getMessageList().size() > 0) {
      return(null);
    } else {
      return ("show-customer1");
    }
  }
}
