package coreservlets.persistence;

import coreservlets.Customer;

public interface CustomerBatchPersistence {

    public void insert(Customer... customers);

    public int getCustomerCount();

}
