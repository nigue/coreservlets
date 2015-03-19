package coreservlets.di.constructor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/di/constructor"
      + "/applicationContext.xml");
    
    System.out.println(beanFactory.getBean("impliedValues"));
    
    System.out.println(beanFactory.getBean("explicitValues"));    
  }
  
}
