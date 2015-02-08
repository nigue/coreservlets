package coreservlets.components;

import coreservlets.utils.DateUtils;
import java.text.DateFormatSymbols;
import java.util.*;

import javax.faces.component.FacesComponent;

@FacesComponent("coreservlets.components.DateComponent2")
public class DateComponent2 extends DateComponent1 {
  private int[] days;
  private int[] years;
  private int startYear = 1900;
  private int endYear = 2100;
  private static Map<String, Integer> months;
  
  static {  // Months never change, but years and days do
    months = new LinkedHashMap<>();
    String[] names = new DateFormatSymbols().getMonths();
    for (int i = 0; i < 12; i++) {
      months.put(names[i], i + 1);
    } 
  }
  
  public DateComponent2() {
    days = DateUtils.intArray(1, 31);
    years = DateUtils.intArray(startYear, endYear);
  }
  
  public int getStartYear() {
    return(startYear);
  }

  public void setStartYear(int startYear) {
    this.startYear = startYear;
    years = DateUtils.intArray(startYear, endYear);
  }

  public int getEndYear() {
    return(endYear);
  }

  public void setEndYear(int endYear) {
    this.endYear = endYear;
    years = DateUtils.intArray(startYear, endYear);
  }
  
  public int[] getDays() { 
    return(days); 
  }
 
  public int[] getYears() { 
    return(years);
  }
  
  public Map<String, Integer> getMonths() { 
    return(months); 
  }
}
