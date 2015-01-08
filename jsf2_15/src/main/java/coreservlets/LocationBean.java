package coreservlets;

import coreservlets.info.StateInfo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean 
@SessionScoped
public class LocationBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private String state, city;
  private boolean isCityListDisabled = true;

  public String getState() {
    return(state);
  }

  public void setState(String state) {
    this.state = state;
    this.city="";                // Clear population display
    isCityListDisabled = false; // Enable list of cities
  }

  public String getCity() {
    return(city);
  }

  public void setCity(String city) {
    this.city = city;
  }
  
  public boolean isCityListDisabled() {
    return(isCityListDisabled);
  }
  
  public List<String> getStates() {
    return(StateInfo.STATE_NAMES);
  }
  
  public Map<String,String> getCities() {
    return(StateInfo.STATE_MAP.get(state));
  }
}
