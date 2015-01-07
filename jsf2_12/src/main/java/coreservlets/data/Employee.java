package coreservlets.data;

public class Employee {
  private Name name;
  private Company company;

  public Employee(Name name, Company company) {
    this.name = name;
    this.company = company;
  }

  public Name getName() { return(name); }

  public Company getCompany() { return(company); }

  public String processEmployee() {
    if (Math.random() < 0.5) {
      return("accepted");
    } else {
      return("rejected");
    }
  }
}