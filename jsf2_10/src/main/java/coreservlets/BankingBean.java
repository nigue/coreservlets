package coreservlets;

import coreservlets.data.Customer;
import coreservlets.map.CustomerSimpleMap;
import coreservlets.service.CustomerLookupService;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class BankingBean {

    protected String customerId, password;
    protected Customer customer;
    private static CustomerLookupService lookupService = // Omit this if you are doing dependency injection!
            new CustomerSimpleMap();

    public String getCustomerId() {
        return (customerId);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId.trim();
        if (this.customerId.isEmpty()) {
            this.customerId = "(none entered)";
        }
    }

    public String getPassword() {
        return (password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return (customer);
    }

    public String showBalance() {
        String folder = "pages/bankingBean/";
        if (!password.equals("secret")) {
            return (folder + "wrong-password");
        }
        customer = lookupService.findCustomer(customerId);
        if (customer == null) {
            return (folder + "unknown-customer");
        } else if (customer.getBalance() < 0) {
            return (folder + "negative-balance");
        } else if (customer.getBalance() < 10000) {
            return (folder + "normal-balance");
        } else {
            return (folder + "high-balance");
        }
    }
}
