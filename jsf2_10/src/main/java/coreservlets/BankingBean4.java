package coreservlets;

import coreservlets.service.CustomerLookupService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class BankingBean4 extends BankingBean {

    @ManagedProperty(value = "#{currentLookupService}")
    private CustomerLookupService service;

    public void setService(CustomerLookupService service) {
        this.service = service;
    }

    @Override
    public String showBalance() {
        String folder = "pages/bankingBean4/";
        if (!password.equals("secret")) {
            return (folder + "wrong-password4");
        }
        customer = service.findCustomer(customerId);
        if (customer == null) {
            return (folder + "unknown-customer4");
        } else if (customer.getBalance() < 0) {
            return (folder + "negative-balance4");
        } else if (customer.getBalance() < 10000) {
            return (folder + "normal-balance4");
        } else {
            return (folder + "high-balance4");
        }
    }
}
