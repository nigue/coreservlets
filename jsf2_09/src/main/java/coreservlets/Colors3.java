package coreservlets;

import javax.faces.bean.ManagedBean;

// This version illustrates that if the getter methods return non-empty values
// initially, that affects initial selection of menu, listbox, or radio buttons.
@ManagedBean
public class Colors3 extends Colors1 {

    public Colors3() {
        color1 = "orange";
        color2 = "#ff0000";
        color3 = "#008000";
        color4 = "#0000ff";
    }

    @Override
    public String showColors() {
        String folder = "pages/colors3/";
        return (folder + "show-colors-3");
    }
}
