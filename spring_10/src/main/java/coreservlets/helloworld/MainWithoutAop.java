package coreservlets.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class MainWithoutAop {
  
  public static void main(String[]args) throws Exception{
    
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
      "spring/coreservletsContext.xml"});
       
    CustomerQuery query = (CustomerQuery) beanFactory.getBean("customerQuery");
    
    Customer customer = query.getCustomerByName("Java Joe");
    
    System.out.println(customer);
    
  } 
}
