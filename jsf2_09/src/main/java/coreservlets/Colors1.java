package coreservlets;

import javax.faces.bean.ManagedBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
public class Colors1 {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(Colors1.class);
    protected String color1, color2, color3, color4;

    public String getColor1() {
        return (color1);
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return (color2);
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return (color3);
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getColor4() {
        return (color4);
    }

    public void setColor4(String color4) {
        this.color4 = color4;
    }

    public String showColors() {
        String folder = "pages/colors1/";
        LOGGER.debug(folder + "show-colors-1");
        return (folder + "show-colors-1");
    }
}
