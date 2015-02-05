package coreservlets;

import coreservlets.bean.Company1;
import coreservlets.bean.Company3;
import coreservlets.bean.Company2;
import coreservlets.data.Company;
import java.util.*;
import javax.faces.bean.*;

@ManagedBean
@ApplicationScoped
public class CompanyInfo {
  private String companyName;
  private Company[] companies = {
    new Company1(), new Company2(), new Company3() };
  private List<String> companyChoices;
  private Map<String, Company> companyMap;

  public CompanyInfo() {
    companyChoices = new ArrayList<>();
    companyMap = new HashMap<>();
    companyName = companies[0].getCompanyName();
    for(Company c: companies) {
      companyChoices.add(c.getCompanyName());
      companyMap.put(c.getCompanyName(), c);
    }
  }
  
  public String getCompanyName() {
    return(companyName);
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  
  public List<String> getCompanyChoices() {
    return (companyChoices);
  }
  
  public Company getCompany() {
    return(companyMap.get(companyName));
  }
}
