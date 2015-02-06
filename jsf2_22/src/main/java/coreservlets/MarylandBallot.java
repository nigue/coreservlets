package coreservlets;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class MarylandBallot extends WriteinBallot {

    public MarylandBallot() {
        super("To require all Maryland elementary schools "
                + "to teach Java programming",
                "To have Java programming replace lacrosse "
                + "as the official state team sport");
    }

    public String checkBallot() {
        Person pres = getPresident();
        Person vp = getVicePresident();
        if (pres.getFirstName().equals(vp.getFirstName())
                && pres.getLastName().equals(vp.getLastName())) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage errorMessage
                    = new FacesMessage("Cannot vote for same person for "
                            + "President and Vice President");
            context.addMessage(null, errorMessage);
            return (null);
        } else {
            return ("show-ballot-vote");
        }
    }
}
