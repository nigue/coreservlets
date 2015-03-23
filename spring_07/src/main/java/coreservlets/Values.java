package coreservlets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Values {

  private List<String>values;
  
  public Values(){
    this.values = new ArrayList<String>();
  }
  
  public Values(String...values){
    this.values = Arrays.asList(values);
  }
  
  public Values(String value){
    this.values = Arrays.asList(value);
  }
  
  public void setValue(String value){
    this.values.add(value);
  }
  
  public String toString(){
    return this.values.toString();
  }
}
