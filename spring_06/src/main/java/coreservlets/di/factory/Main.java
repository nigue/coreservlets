package coreservlets.di.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/di/factory"
      + "/applicationContext.xml");
    
    System.out.println(beanFactory.getBean("values"));
    
  }  
}
