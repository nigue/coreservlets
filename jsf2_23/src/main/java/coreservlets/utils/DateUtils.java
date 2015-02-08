package coreservlets.utils;

import java.util.Date;

// Some code adapted from date class in Chapter 9 of "Core JavaServer Faces, 3rd Edition".

public class DateUtils {
  public static long millisInDay() {
    return(24*60*60*1000);
  }
  
  public static Date nextDay(Date date) {
    return(new Date(date.getTime() + millisInDay()));
  }
  
  public static int[] intArray(int from, int to) {
    int[] nums = new int[to - from + 1];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = from++;
    }
    return (nums);
  }
  
  public static int daysInMonth(int month, int year) {
    if (month == 2) {
      if (isLeapYear(year)) {
        return (29);
      } else {
        return (28);
      }
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      return (30);
    } else {
      return (31);
    }
  }
 
  private static boolean isLeapYear(int year) {
    return((year % 4 == 0) && 
           ((year % 400 == 0) || (year % 100 != 0)));
  }
  
  private DateUtils() {} // Uninstantiatable class
}
