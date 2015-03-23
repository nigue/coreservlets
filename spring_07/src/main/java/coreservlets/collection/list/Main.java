package coreservlets.collection.list;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) { 
    
    new ClassPathXmlApplicationContext(
      "spring/collection/list"
      + "/applicationContext.xml");
    
  }  
}
