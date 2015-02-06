package coreservlets.data;

/** Bean to represent a software developer. 
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF 2.0, Ajax, GWT, 
 *  Spring, Hibernate/JPA, Web Services, and Java programming</a>.
 */

public class Programmer {
  private String firstName, lastName, level;
  private String[] languages;

  public Programmer(String firstName,
                    String lastName,
                    String level,
                    String... languages) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.level = level;
    this.languages = languages;
  }

  public String getFirstName() {
    return(firstName);
  }

  public String getLastName() {
    return(lastName);
  }

  public String getLevel() {
    return(level);
  }
  
  public String[] getLanguages() {
    return(languages);
  }
}
  
