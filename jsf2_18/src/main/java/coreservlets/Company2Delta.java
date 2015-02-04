package coreservlets;

import coreservlets.data.use.Person1;
import coreservlets.data.Programmer;
import javax.faces.bean.ManagedBean;

@ManagedBean(eager = true)
public class Company2Delta {

    private Programmer[] programmers;

    public Company2Delta() {
        //Programmer person1 = new Person1();
        
        this.programmers[0] = new Person1();
    }
    
    public Programmer[] getProgrammers() {
        return (programmers);
    }

}
