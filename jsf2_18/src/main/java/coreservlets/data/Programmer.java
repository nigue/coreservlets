package coreservlets.data;

/**
 * Bean to represent a software developer.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF 2.0, Ajax, GWT,
 * Spring, Hibernate/JPA, Web Services, and Java programming</a>.
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
        return (firstName);
    }

    public String getLastName() {
        return (lastName);
    }

    public String getLevel() {
        return (level);
    }

    public String[] getLanguages() {
        return (languages);
    }

    public String getLanguageList1() {
        StringBuilder langList = new StringBuilder();
        for (int i = 0; i < languages.length; i++) {
            if (i < (languages.length - 1)) {
                langList.append(languages[i] + ", ");
            } else {
                langList.append("and " + languages[i]);
            }
        }
        return (langList.toString());
    }

    public String getLanguageList2() {
        String span
                = String.format("<span class='languages'>%s</span>",
                        getLanguageList1());
        return (span);
    }
}
