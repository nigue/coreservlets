package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MessageBean {
  private String message, emailAddress;

  public String getMessage() {
    return(message);
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getEmailAddress() {
    return(emailAddress);
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String showMessage() {
    return("show-message");
  }
}
