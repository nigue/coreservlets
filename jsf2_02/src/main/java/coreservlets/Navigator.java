package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Navigator {

    public String choosePage() {
        
        String folder = "pages/navigator/";
        
        if (Math.random() > 0.5) {
            return (folder + "result-page-1");
        } else {
            return (folder + "result-page-2");
        }
    }
}
