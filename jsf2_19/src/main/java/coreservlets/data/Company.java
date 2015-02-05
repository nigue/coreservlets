package coreservlets.data;

public class Company {
  private String companyName;
  private Programmer[] programmers;
  
  public Company(String companyName,
                 Programmer... programmers) {
    this.companyName = companyName;
    this.programmers = programmers;
  }
  
  public String getCompanyName() {
    return (companyName);
  }

  public Programmer[] getProgrammers() {
    return (programmers);
  }
}
