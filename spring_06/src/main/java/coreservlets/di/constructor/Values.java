package coreservlets.di.constructor;

public class Values {
  
  private String stringValue;
  
  private Integer integerValue;

  public Values(Integer integerValue, String stringValue){
    this.integerValue = integerValue;
    this.stringValue = stringValue;
  }

  public Values(String stringValue, Integer integerValue){
    this.stringValue = stringValue;
    this.integerValue = integerValue;
  }
  
  public String getStringValue() {
    return stringValue;
  }

  public Integer getIntegerValue() {
    return integerValue;
  }
  
  public String toString(){
    return "stringValue=" + this.stringValue
    + " integerValue=" + this.integerValue;
  }
}
