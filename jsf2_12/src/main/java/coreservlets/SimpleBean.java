package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SimpleBean {

    private String[] colors
            = {"red", "orange", "yellow"};

    public String getMessage() {
        return ("Hello, World");
    }

    public String[] getColors() {
        return (colors);
    }
}
