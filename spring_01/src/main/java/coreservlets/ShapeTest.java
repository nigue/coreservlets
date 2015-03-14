package coreservlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Driver class. This is the only class that should import Spring packages or
 * depend on Spring in any way.
 *
 * From <a href="http://courses.coreservlets.com/Course-Materials/">
 * the coreservlets.com Java EE tutorials</a>.
 */
public class ShapeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShapeTest.class);

    public static void main(String[] args) {
        ApplicationContext context;
        try {
            context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
            Shape shape1 = (Shape) context.getBean("shape1");
            printInfo(shape1);
            Shape shape2 = (Shape) context.getBean("shape2");
            printInfo(shape2);
        } catch (BeansException be) {
            LOGGER.debug(be.getLocalizedMessage());
        }
    }

    private static void printInfo(Shape shape) {
        System.out.printf("%s with area of %,.2f%n",
                shape.getClass().getSimpleName(),
                shape.getArea());
    }
}
