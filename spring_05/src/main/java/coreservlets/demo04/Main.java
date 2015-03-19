package coreservlets.demo04;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demonstrates an ApplicationContext event callback.
 */
public class Main {

  public static void main(String[] args) {
  
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
        "spring/demo04/applicationContext.xml");
    
    ConfigurableApplicationContext configurableContext = 
      (ConfigurableApplicationContext) beanFactory;
    
    configurableContext.start();
    
    configurableContext.stop();

    configurableContext.close();
    
  }
}
