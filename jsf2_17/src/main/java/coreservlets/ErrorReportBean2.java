package coreservlets;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ErrorReportBean2 extends ErrorReportBean1 {
  @Override
  public String sendReport() {
    System.out.println("in ErrorReportBean2.sendReport");
    super.sendReport();
    String confirmationMessage = String.format(
        "%s (%s) reported \"%s\" for %s. On confirmation " +
        "of problem, $10 will be credited to xxxx-%s.",
         getName(), getEmailAddress(), getProblemDescription(), 
         getUrl(), getAbbreviatedCardNumber());
    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage fMessage = new FacesMessage(confirmationMessage);
    context.addMessage(null, fMessage);
    return(null);
  }
}
