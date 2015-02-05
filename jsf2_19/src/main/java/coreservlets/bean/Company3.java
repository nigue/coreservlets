package coreservlets.bean;

import coreservlets.data.Company;
import coreservlets.data.Programmer;

public class Company3 extends Company {
  public Company3() {
    super("Third-Company.com", 
          new Programmer("Stephen", "Harper", "Intermediate", 123456.78,
                        "C#", "Java", "JavaScript", "Perl"),
          new Programmer("Barack", "Obama", "Junior", 112345.67,
                         "Lisp", "Java", "C++"),
          new Programmer("Enrique", "Peña Nieto", "Senior", 134567.89,
                         "Python", "Java", "Ruby", "JRuby"));
  }
}
