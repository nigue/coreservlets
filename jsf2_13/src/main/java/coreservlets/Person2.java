package coreservlets;

import coreservlets.data.Person;
import javax.faces.bean.*;

@ManagedBean
public class Person2 extends Person {
  @Override
  public String doRegistration() {
    return("success2");
  }
}
