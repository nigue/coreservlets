package coreservlets;

import coreservlets.data.Programmer;
import javax.faces.bean.*;

@ManagedBean
public class Person2 extends Programmer {
  public Person2() {
    super("Larry", "Page", "Junior",
          "Java", "C++", "Python", "Go");
  }
}
