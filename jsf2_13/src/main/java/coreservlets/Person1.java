package coreservlets;

import coreservlets.data.Person;
import javax.faces.bean.*;

@ManagedBean
public class Person1 extends Person {
  @Override
  public String doRegistration() {
    return("success1");
  }
}
