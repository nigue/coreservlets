package coreservlets.bean;

import coreservlets.service.CustomerLookupService;
import coreservlets.preferences.ColorPreferences;
import coreservlets.data.Customer;

public class CustomerBackingBean {
  private String inputID, password;
  private Customer customer;
  private ColorPreferences colorPreferences;
  private CustomerLookupService lookupService;

  public String getInputID() {
    return(inputID);
  }

  public void setInputID(String inputID) {
    this.inputID = inputID;
  }

  public String getPassword() {
    return(password);
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public Customer getCustomer() {
    return(customer);
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public ColorPreferences getColorPreferences() {
    return(colorPreferences);
  }

  public void setColorPreferences(ColorPreferences colorPreferences) {
    this.colorPreferences = colorPreferences;
  }
  
  public CustomerLookupService getLookupService() {
    return(lookupService);
  }

  public void setLookupService(CustomerLookupService lookupService) {
    this.lookupService = lookupService;
  }
  
  public String getBalance() {
    if (isEmpty(inputID)) {
      return("missing-id");
    } else {
      customer = lookupService.getCustomer(inputID);
      if (customer == null) {
        return("invalid-id");
      } else {
        return("show-balance");
      }
    }
  }
  
  private boolean isEmpty(String value) {
    return((value == null) || (value.trim().equals("")));
  }
}
