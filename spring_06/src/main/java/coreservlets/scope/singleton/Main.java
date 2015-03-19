package coreservlets.scope.singleton;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory = 
      new ClassPathXmlApplicationContext(
        "spring/scope/singleton/applicationContext.xml");

    Object a = beanFactory.getBean("bookLibrary");
    Object b = beanFactory.getBean("bookLibrary");
    Object c = beanFactory.getBean("bookLibrary");
    Object d = beanFactory.getBean("bookLibrary");

    System.out.printf("%s, %s, %s%n", a == b, b == c, c == d);    
  }
}
