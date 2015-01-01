package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SomeBean {

    private String someProperty = "Blah, blah";

    public String getSomeProperty() {
        return (someProperty);
    }

    public void setSomeProperty(String someProperty) {
        this.someProperty = someProperty;
    }

    public String someActionControllerMethod() {

        String folder = "pages/";
        return (folder + "page-b");  // Means to go to page-b.xhtml (since condition is not mapped in faces-config.xml)
    }

    public String someOtherActionControllerMethod() {
        return ("../index");  // Means to go to index.xhtml (since condition is not mapped in faces-config.xml)
    }
}
