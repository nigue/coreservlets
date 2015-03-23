package coreservlets.reference.value;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) { 
    
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/reference/value"
      + "/applicationContext.xml");
    
    System.out.println(beanFactory.getBean("values-00"));
    System.out.println(beanFactory.getBean("values-01"));
    System.out.println(beanFactory.getBean("values-02"));
    System.out.println(beanFactory.getBean("values-03"));
    
  }  
}
