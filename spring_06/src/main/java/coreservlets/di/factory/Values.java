package coreservlets.di.factory;


public class Values {

  private String stringValue;
  
  private Integer integerValue;

  public Values(Integer integerValue, String stringValue){
    this.integerValue = integerValue;
    this.stringValue = stringValue;
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
