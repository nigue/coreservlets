package coreservlets;

import javax.faces.bean.*;

@ManagedBean
@ApplicationScoped
public class TestBean2 {
  private final String HELLO_ENGLISH = "Hello!";
  private final String HELLO_SPANISH = "Hola!";

  public String greeting(boolean useSpanish) {
    if (useSpanish) {
      return(HELLO_SPANISH); 
    } else {
      return(HELLO_ENGLISH);
    }
  }
  
  public String greeting() {
    return(greeting(false));
  }

  public double randomNumber(double range) {
    return(range * Math.random());
  }
  
  
  // The EL won't let me call this with individual args.
  // Is this a bug?
  public int min(int ... numbers) {
    int minimum = Integer.MAX_VALUE;
    for(int number: numbers) {
      if (number < minimum) {
        minimum = number;
      }
    }
    return(minimum);
  }
}