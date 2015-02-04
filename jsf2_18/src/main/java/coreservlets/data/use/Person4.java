package coreservlets.data.use;

import coreservlets.data.Programmer;
import javax.faces.bean.*;

@ManagedBean
public class Person4 extends Programmer {
  public Person4() {
    super("Sam", "Palmisano", "Intermediate",
          "REXX", "CLIST", "Java", "PL/I", "COBOL");
  }
}
