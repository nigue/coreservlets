package coreservlets;

/** Main parent class. Note that in the simpler spring-intro project,
 *  we used an interface. Here, we changed it to an abstract class so
 *  that the printInfo method could be put here.
 *  
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">
 *  the coreservlets.com Java EE tutorials</a>.
 */

public abstract class Shape {
  public abstract double getArea();
  
  public void printInfo() {
    System.out.printf("%s with area of %,.2f%n", 
                      getClass().getSimpleName(),
                      getArea());
  }
}
