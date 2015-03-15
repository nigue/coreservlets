package coreservlets;

import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 * Driver class that compares the effects of different bean scopes.
 *
 * From <a href="http://courses.coreservlets.com/Course-Materials/">
 * the coreservlets.com Java EE tutorials</a>.
 */
public class ScopeTest {

    public static void main(String[] args) {
        System.out.println("Singleton beans from same context");
        singletonTest1("rectangle1");
        System.out.println("Prototype beans from same context");
        singletonTest1("rectangle2");
        System.out.println("Singleton beans from different contexts");
        singletonTest2("rectangle1");
    }

    public static void singletonTest1(String beanName) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("spring/scope-test.xml");
        Rectangle r1
                = (Rectangle) context.getBean(beanName);
        Rectangle r2
                = (Rectangle) context.getBean(beanName);
        compareRectangles(r1, r2);
        r1.setLength(50);
        compareRectangles(r1, r2);
    }

    public static void compareRectangles(Rectangle r1, Rectangle r2) {
        System.out.print("  r1: ");
        r1.printInfo();
        System.out.print("  r2: ");
        r2.printInfo();
        System.out.printf("  r1 == r2: %s%n", r1 == r2);
    }

    public static void singletonTest2(String beanName) {
        ApplicationContext context1
                = new ClassPathXmlApplicationContext("spring/scope-test.xml");
        Rectangle r1
                = (Rectangle) context1.getBean(beanName);
        ApplicationContext context2
                = new ClassPathXmlApplicationContext("spring/scope-test.xml");
        Rectangle r2
                = (Rectangle) context2.getBean(beanName);
        compareRectangles(r1, r2);
        r1.setLength(50);
        compareRectangles(r1, r2);
    }
}
