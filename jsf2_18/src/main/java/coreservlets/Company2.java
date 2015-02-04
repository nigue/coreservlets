package coreservlets;

import coreservlets.data.use.Person3;
import coreservlets.data.use.Person4;
import coreservlets.data.use.Person2;
import coreservlets.data.use.Person5;
import coreservlets.data.use.Person1;
import coreservlets.data.Programmer;
import javax.faces.bean.*;

@ManagedBean(eager = true)
public class Company2 {

    private final Programmer[] programmers = {
        new Person1(), new Person2(), new Person3(),
        new Person4(), new Person5()
    };

    public Programmer[] getProgrammers() {
        return (programmers);
    }
}
