package coreservlets;

import java.util.ArrayList;
import java.util.List;



/**
 * Mock CustomerQuery implementation.
 */
public class MockCustomerQuery implements CustomerQuery {

  /** Cached customers */
  private List<Customer> customers;

  /**
   * Constructs a new MockCustomerQuery instance initialized with the argued
   * customer list.
   * 
   * @param customers no requirements
   */
  public MockCustomerQuery(List<Customer> customers) {
    this.customers = customers != null ? customers : new ArrayList<Customer>();
  }

  /*
   * @see coreservlets.CustomerQuery#getCustomerByName(java.lang.String)
   */
  public Customer getCustomerByName(String name) {
    for(Customer c : customers){
      if(c.getName().equals(name)){
        return c;
      }
    }
    return null;
  }
}
