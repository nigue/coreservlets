package coreservlets.di.factory;


public class ValuesFactory {

  private String stringValue;
  
  private Integer integerValue;

  public Values newValuesInstance() {
    return new Values(this.integerValue, this.stringValue);
  }
  
  public void setIntegerValueFromString(String string) {
    this.integerValue = Integer.parseInt(string);
  }
  
  public void setIntegerValue(Integer integerValue) {
    this.integerValue = integerValue;
  }
  
  public void setStringValue (String stringValue) {
    this.stringValue = stringValue;
  }
  
  public void setStringValueFromInteger (Integer integer) {
    this.stringValue = integer.toString();
  }
}
