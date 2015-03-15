package coreservlets;

import org.springframework.context.*;
import org.springframework.context.support.*;

/** Driver class. This is the only class that should import Spring 
 *  packages or depend on Spring in any way.
 *  
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">
 *  the coreservlets.com Java EE tutorials</a>.
 */

public class ShapeTest {
  public static void main(String[] args) {
    ApplicationContext context =
      new ClassPathXmlApplicationContext
                            ("spring/applicationContext2.xml");
    for(int i=1; i<=4; i++) {
      Shape shape = (Shape)context.getBean("shape" + i);
      shape.printInfo();
    }
    for(int i=1; i<=2; i++) {
      ShapeList shapes = 
        (ShapeList)context.getBean("shapeList" + i);
      shapes.printInfo();
    }
  }
}