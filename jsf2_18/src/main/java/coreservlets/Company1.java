package coreservlets;

import coreservlets.data.Programmer;
import coreservlets.data.use.Person1;
import coreservlets.data.use.Person2;
import coreservlets.data.use.Person3;
import java.util.*;
import javax.faces.bean.*;

@ManagedBean
@ApplicationScoped
public class Company1 {
  private List<Programmer> programmers; 
  
  public Company1() {
    programmers = new ArrayList<>();
    programmers.add(new Person1());
    programmers.add(new Person2());
    programmers.add(new Person3());
  }
  
  public List<Programmer> getProgrammers() {
    return(programmers);
  }
}