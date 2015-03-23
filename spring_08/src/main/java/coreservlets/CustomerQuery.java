package coreservlets;

public interface CustomerQuery {
  
  /**
   * Retrieves a stored customer with a matching name.
   * 
   * @param name required
   * @return a non-null value if a stored customer with a matching name is
   * identified. Otherwise, null.
   */
  public Customer getCustomerByName(String name);
  
}
