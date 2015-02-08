package coreservlets.components;

import java.io.IOException;
import java.util.*;
import javax.faces.component.*;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

// Code adapted from date class in Chapter 9 of "Core JavaServer Faces, 3rd Edition".

@FacesComponent("coreservlets.components.DateComponent1")
public class DateComponent1 extends UIInput 
                            implements NamingContainer {
  @Override
  public String getFamily() {
    return("javax.faces.NamingContainer");
  }

  @Override
  public Object getSubmittedValue() {
    return(this);
  }
  
  @Override
  public void encodeBegin(FacesContext context) throws IOException {
    Date date = (Date)getValue();
    Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    UIInput dayComponent = (UIInput)findComponent("day");
    UIInput monthComponent = (UIInput)findComponent("month");
    UIInput yearComponent = (UIInput)findComponent("year");
    dayComponent.setValue(cal.get(Calendar.DATE));
    monthComponent.setValue(cal.get(Calendar.MONTH) + 1);
    yearComponent.setValue(cal.get(Calendar.YEAR));
    super.encodeBegin(context);
  }

  // NOTE: It is possible for the end user to enter an invalid date (e.g., February 31).
  //       This will wrap around into the next month, and result in something like March 3.
  //       We will fix this in a later example where we use Ajax to make sure the number
  //       of days matches the month and year.
  @Override
  protected Object getConvertedValue(FacesContext context, Object newSubmittedValue) 
      throws ConverterException {
    UIInput dayComponent = (UIInput)findComponent("day");
    UIInput monthComponent = (UIInput)findComponent("month");
    UIInput yearComponent = (UIInput)findComponent("year");
    int day = 
      Integer.parseInt((String)dayComponent.getSubmittedValue());
    int month = 
      Integer.parseInt((String)monthComponent.getSubmittedValue());
    int year = 
      Integer.parseInt((String)yearComponent.getSubmittedValue());
    return(new GregorianCalendar(year, month-1, day).getTime());
  }
}