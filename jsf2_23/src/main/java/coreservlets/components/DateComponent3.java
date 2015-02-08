package coreservlets.components;

import coreservlets.utils.DateUtils;
import java.util.*;
import javax.faces.component.FacesComponent;

// TODO: Add JavaDoc.

@FacesComponent("coreservlets.components.DateComponent3")
public class DateComponent3 extends DateComponent2 {
  private static final int[] DAYS_28 = 
    DateUtils.intArray(1, 28);
  private static final int[] DAYS_29 = 
    DateUtils.intArray(1, 29);
  private static final int[] DAYS_30 = 
    DateUtils.intArray(1, 30);
  private static final int[] DAYS_31 = 
    DateUtils.intArray(1, 31);
  
  @Override
  public int[] getDays() { 
    Date date = (Date)getValue();
    Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);
    int daysInMonth = DateUtils.daysInMonth(month, year);
    switch (daysInMonth) {
      case 28: return(DAYS_28);
      case 29: return(DAYS_29);
      case 30: return(DAYS_30);
      default: return(DAYS_31);
    }
  }
}
