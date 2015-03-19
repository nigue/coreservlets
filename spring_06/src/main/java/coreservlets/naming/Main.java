package coreservlets.naming;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/naming"
      + "/applicationContext.xml");
        
    assertBeanName(beanFactory
      , "beanId"
      , "beanName01"
      , "beanName02"
      , "beanName03"
      , "beanName04"
      , "beanAlias");
  }
  
  private static void assertBeanName(
      BeanFactory beanFactory, String...beanNames){
    for(String beanName : beanNames){
      Object bean = beanFactory.getBean(beanName);
      if(bean == null){
        throw new IllegalArgumentException("Invalid: bean name."
          + " Not found: " + beanName);
      }
      System.out.println(beanName + ":" + bean.getClass().getName());
    }
  }
}
