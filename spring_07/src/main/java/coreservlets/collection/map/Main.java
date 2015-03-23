package coreservlets.collection.map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) { 
    
    new ClassPathXmlApplicationContext(
      "spring/collection/map"
      + "/applicationContext.xml");
    
  }  
}
