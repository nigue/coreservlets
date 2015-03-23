package coreservlets.reference.beanidref;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.BeanNames;

public class Main {
  public static void main(String[] args) { 
    
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/reference/beanidref"
      + "/applicationContext.xml");
    
    BeanNames beanNames = (BeanNames) beanFactory.getBean("beanNames");
    
    System.out.println(beanNames);
    
  }  
}
