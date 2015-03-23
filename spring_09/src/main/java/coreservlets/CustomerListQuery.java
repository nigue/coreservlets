package coreservlets;

import java.util.List;


public interface CustomerListQuery {
  
  /**
   * Retrieves all stored customers as a list.
   * 
   * @return a non-null value
   */
  public List<Customer> getCustomers();
    
}
