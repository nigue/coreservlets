package coreservlets;

import java.util.ArrayList;
import java.util.List;

public class BeanNames {

  private List<String> beanNames;

  public BeanNames(String beanName, String... beanNames) {
    this.beanNames = new ArrayList<String>();
    if(beanName == null){
      throw new IllegalArgumentException("Error: minimum of one bean name.");
    }
    this.beanNames.add(beanName);
    if(beanNames != null){
      for(String name : beanNames){
        this.beanNames.add(name);
      }
    }
  }

  public List<String> getBeanNames(){
    return this.beanNames;
  }
  
  public String toString(){
    return this.beanNames.toString();
  }
}
