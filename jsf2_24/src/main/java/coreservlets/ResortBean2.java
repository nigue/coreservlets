package coreservlets;

import coreservlets.utils.DateUtils;
import java.util.Date;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ResortBean2 extends ResortBean {

  @Override
  public void setStartDate(Date startDate) {
    // In SimpleDateFormat, days of month start at 0. But the jQueryUI DatePicker
    // and normal English convention is to start at 1. So, add 1.
    super.setStartDate(DateUtils.nextDay(startDate));
  }

  @Override
  public void setEndDate(Date endDate) {
    // In SimpleDateFormat, days of month start at 0. But the jQueryUI DatePicker
    // and normal English convention is to start at 1. So, add 1.
    super.setEndDate(DateUtils.nextDay(endDate));
  }
  
  // If return value is null, leave it unchanged (which means to redisplay input form).
  // If return value is "show-dates", put a 2 on the end (i.e., "show-dates2"), because
  // the results page for this example uses resortBean2 instead of resortBean.
  
  @Override
  public String register() {
    String returnValue = super.register();
    if (returnValue != null) {
      returnValue = returnValue + "2";
    }
    return(returnValue);
  }
}
