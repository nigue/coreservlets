package coreservlets;

import javax.faces.bean.*;

@ApplicationScoped
@ManagedBean
public class SportsBean {
  public String getRavens() {
    return("First place");
  }
  
  public String getRedskins() {
    return("Last place");
  }

  public String getOrioles() {
    return("Last place");
  }
  
  public String getNationals() {
    return("First place");
  }
  
  public String getPhelps() {
    return("Major winner");
  }
  
  public String getLochte() {
    return("Minor winner");
  }
}
