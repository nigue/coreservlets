package coreservlets.demo04;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class SimpleApplicationListener 
implements ApplicationListener {
  
  public void onApplicationEvent(ApplicationEvent event) {
     System.out.printf("Event type: %s%n", event.getClass().getSimpleName());
  }
  
}
