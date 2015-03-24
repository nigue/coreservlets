package coreservlets.pointcut.execution;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.CustomerQuery;

public class Main {
  
  public static void main(String[]args) throws Exception{
    
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
      "spring/pointcut/execution/coreservletsAopContext.xml",
      "spring/coreservletsContext.xml"});
       
    CustomerQuery query = (CustomerQuery) beanFactory.getBean("customerQuery");
    
    query.getCustomerByName("Java Joe");
  }
  
}
