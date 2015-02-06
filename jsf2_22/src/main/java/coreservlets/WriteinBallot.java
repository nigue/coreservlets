package coreservlets;

public class WriteinBallot {
  private final Person president, vicePresident;
  private boolean forProp1, forProp2;
  private final String prop1Description, prop2Description;
 
  public WriteinBallot(String prop1Description, 
                       String prop2Description) {
    this.prop1Description = prop1Description;
    this.prop2Description = prop2Description;
    forProp1 = true;
    forProp2 = true;
    president = new Person();
    vicePresident = new Person();
  }
  
  public Person getPresident() {
    return (president);
  }
  
  public Person getVicePresident() {
    return (vicePresident);
  }
  
  public boolean isForProp1() {
    return (forProp1);
  }
  
  public void setForProp1(boolean forProp1) {
    this.forProp1 = forProp1;
  }
  
  public String getProp1Description() {
    return (prop1Description);
  } 
  
  public boolean isForProp2() {
    return (forProp2);
  }
  
  public void setForProp2(boolean forProp2) {
    this.forProp2 = forProp2;
  }
  
  public String getProp2Description() {
    return (prop2Description);
  }
}
