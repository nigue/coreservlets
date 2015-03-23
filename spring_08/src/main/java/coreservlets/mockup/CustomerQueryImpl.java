package coreservlets.mockup;

import java.util.List;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class CustomerQueryImpl implements CustomerQuery {

    private List<Customer> customers;

    public CustomerQueryImpl(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public Customer getCustomerByName(String name) {
        for (Customer c : customers) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
