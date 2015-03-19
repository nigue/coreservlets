package coreservlets.di.autowiring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.BookReader;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/di/autowiring"
      + "/applicationContext.xml");
    
    BookReader bookReader = (BookReader) beanFactory.getBean("bookReader");
    
    bookReader.read();    
  }
}
