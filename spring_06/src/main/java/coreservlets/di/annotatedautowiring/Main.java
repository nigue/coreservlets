package coreservlets.di.annotatedautowiring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/di/annotatedautowiring"
      + "/applicationContext.xml");
    
    AnnotatedBookReader bookReader = (AnnotatedBookReader)
      beanFactory.getBean("annotatedBookReader");
    bookReader.read();    
    
  }
}
