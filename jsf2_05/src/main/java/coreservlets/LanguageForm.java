package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LanguageForm {

    private String language;

    public String getLanguage() {
        return (language);
    }

    public void setLanguage(String language) {
        this.language = language.trim();
    }

    public String showChoice() {
        String folder = "pages/languageForm/";
        if (isMissing(language)) {
            return (folder + "missing-language");
        } else if (language.equalsIgnoreCase("Java")
                || language.equalsIgnoreCase("Groovy")) {
            return (folder + "good-language");
        } else {
            return (folder + "bad-language");
        }
    }

    private boolean isMissing(String value) {
        return ((value == null) || (value.trim().isEmpty()));
    }
}
