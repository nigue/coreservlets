package coreservlets;

public interface CustomerUpdate {
  
  /**
   * Saves the specified customer information.
   * 
   * @param customer required
   */
  public void save(Customer customer);
  
  /**
   * Deletes the specified customer by name
   * 
   * @param customerId required
   */
  public void deleteById (String customerId);
}
