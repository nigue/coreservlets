package coreservlets;

import coreservlets.data.Nameable;
import coreservlets.utils.DateUtils;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

// TODO: Add JavaDoc.

@ManagedBean
public class ResortBean implements Nameable {
  private String firstName, lastName;
  private Date startDate=DateUtils.nextDay(new Date()),  // Check in tomorrow at earliest
               endDate=DateUtils.nextDay(startDate);     // One day is minimum stay
  
  @Override
  public String getFirstName() {
    return(firstName);
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return(lastName);
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  // The startDate and endDate bean properties
  // are of type Date. Automatically
  // converted by the backing component.
  
  public Date getStartDate() {
    return(startDate);
  }
  
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  
  public Date getEndDate() {
    return(endDate);
  }
  
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  /** Returns a String representing the starting date, in a form similar to
   * "Tuesday, November 1, 2011". For use in results page.
   */
  public String getStartDay() {
    return(formatDate(startDate));
  }
  
  /** Given a Date, returns a String "Day, Month Number, Year", 
   *  e.g. "Wednesday, November 2, 2011". For results page.
   */
  private String formatDate(Date date) {
    if (date == null) {
      return("");
    } else {
      return(String.format("%tA, %tB %te, %tY", 
                           date, date, date, date));
    }
  }

  /** Returns a String representing the ending date, in a form similar to
   * "Wednesday, November 2, 2011". For use in results page.
   */
  public String getEndDay() {
    return(formatDate(endDate));
  }
  
  public String register() {
    FacesContext context = FacesContext.getCurrentInstance();
    if (!startDate.before(endDate)) {
      endDate = DateUtils.nextDay(startDate);
      FacesMessage errorMessage = 
        new FacesMessage("End date must be after start date");
      context.addMessage("registrationForm:checkoutDate", 
                         errorMessage);
      return(null);
    } else {
      return("show-dates");
    }
  }
}
