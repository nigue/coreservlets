package coreservlets.data;

public class Company {
  private String companyName, business;

  public Company(String companyName, String business) {
    this.companyName = companyName;
    this.business = business;
  }

  public String getCompanyName() { return(companyName); }

  public void setCompanyName(String newCompanyName) {
    companyName = newCompanyName;
  }

  public String getBusiness() { return(business); }

  public void setBusiness(String newBusiness) {
    business = newBusiness;
  }
}
