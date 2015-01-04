package coreservlets;

import coreservlets.utils.RandomUtils;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ApplicationScoped  // See Managed Beans II lecture for explanation of this minor optimization. Can be omitted.  
public class Navigator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Navigator.class);
    private String[] resultPages = {"page1", "page2", "page3"};

    public String choosePage() {
        String folder = "pages/navigator/";
        folder = folder + RandomUtils.randomElement(resultPages);
        LOGGER.debug(folder);
        return (folder);
    }
}
