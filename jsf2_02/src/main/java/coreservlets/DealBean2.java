package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DealBean2 extends DealBean1 {

    /**
     * Same as old version except changes return-value-blah-1 to
     * return-value-blah-2. Done so that we can replace the facelets pages
     * without changing the Java code.
     *
     * @return
     */
    @Override
    public String buyDailyDeal() {
        String value = super.buyDailyDeal();
        return (value.replace("1", "2"));
    }
}
