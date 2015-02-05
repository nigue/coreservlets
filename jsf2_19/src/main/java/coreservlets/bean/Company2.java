package coreservlets.bean;

import coreservlets.data.Company;
import coreservlets.data.Programmer;

public class Company2 extends Company {
  public Company2() {
    super("Some-Other-Company.com", 
          new Programmer("Harry", "Hacker", "Junior", 12345.67,
                         "Java", "C++", "JavaScript"),
          new Programmer("Polly", "Programmer", "Intermediate", 23456.78,
                         "Java", "C++", "JavaScript", "Ruby"),
          new Programmer("Codie", "Coder", "Senior", 34567.89,
                         "C#", "Java", "Python", "JavaScript"));
  }
}
