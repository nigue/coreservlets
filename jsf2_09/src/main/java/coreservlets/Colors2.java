package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "myBean")
public class Colors2 extends Colors1 {

    @Override
    public String showColors() {
        String folder = "pages/colors2/";
        return (folder + "show-colors-2");
    }
}
