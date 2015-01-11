package coreservlets;

import coreservlets.utils.DateUtils;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ReservationBean1 {
  private Date startDate, endDate;
  
  public Date getStartDate() {
    if (startDate == null) {
      startDate = new Date();
    }
    return(startDate);
  }
  
  /** JSF (or just Mojarra?) has off-by-1 error in parsing date strings, so add 1 day to incoming dates. */
  
  public void setStartDate(Date startDate) {
    this.startDate = DateUtils.nextDay(startDate);
  }
  
  public Date getEndDate() {
    if (endDate == null) {
      endDate = DateUtils.nextDay(getStartDate());
    }
    return(endDate);
  }
  
  /** JSF (or just Mojarra?) has off-by-1 error in parsing date strings, so add 1 day to incoming dates. */
  
  public void setEndDate(Date endDate) {
    this.endDate = DateUtils.nextDay(endDate);
  }
  
  /** Returns a String representing the starting date, in a form similar to
   * "Friday, January 30, 2015". For use in results page.
   */
  public String getStartDay() {
    return(DateUtils.formatDay(startDate));
  }
  
  /** Returns a String representing the ending date, in a form similar to
   * "Friday, January 30, 2015". For use in results page.
   */
  public String getEndDay() {
    return(DateUtils.formatDay(endDate));
  }
  
  private void putReservationInDatabase() {
    // Store result
  }

  public String register() {
    String messageText = null;
    Date today = new Date();
    if (startDate.before(today)) {
      messageText = "Arrival cannot be in the past";
    } else if (!startDate.before(endDate)) {
      messageText = "Departure must be after arrival";
    }
    if (messageText != null) {
      startDate = null;
      endDate = null;
      FacesMessage errorMessage = new FacesMessage(messageText);
      errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
      return(null);
    } else {
      putReservationInDatabase();
      return("show-dates-1");
    }
  }
}
