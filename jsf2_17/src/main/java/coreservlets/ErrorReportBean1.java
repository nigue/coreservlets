package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ErrorReportBean1 {
  private String name, emailAddress, url, problemDescription,
                 cardNumber="", cardNumberConfirmation;

  public String getName() {
    return(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailAddress() {
    return(emailAddress);
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getUrl() {
    return(url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getProblemDescription() {
    return(problemDescription);
  }

  public void setProblemDescription(String problemDescription) {
    this.problemDescription = problemDescription;
  }

  public String getCardNumber() {
    return(cardNumber);
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  private String abbreviatedCardNumber(String fullCardNumber) {
    int length = fullCardNumber.length();
    if(length > 3) {
      return(fullCardNumber.substring(length-4));
    } else {
      return(fullCardNumber);
    }
  }
  
  public String getAbbreviatedCardNumber() {
    return(abbreviatedCardNumber(cardNumber));
  }
  
  public String getCardNumberConfirmation() {
    return(cardNumberConfirmation);
  }

  public void setCardNumberConfirmation(String cardNumberConfirmation) {
    this.cardNumberConfirmation = cardNumberConfirmation;
  }
  
  protected void storeReportInDatabase() {
    // Store the error report
  }
  
  public String sendReport() {
    storeReportInDatabase();
    return("report-confirmation");
  }
}
