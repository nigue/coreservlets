package coreservlets.persistence;

import javax.sql.DataSource;

import coreservlets.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringJdbcCustomerBatchPersistence implements CustomerBatchPersistence {

  private JdbcTemplate simpleJdbc;

  public SpringJdbcCustomerBatchPersistence(DataSource dataSource) {
    this.simpleJdbc = new JdbcTemplate(dataSource);
  }
  
  @Override
  public int getCustomerCount(){
    return simpleJdbc.queryForObject("select count(*) from customer", Integer.class);
  }

  @Override
  public void insert(Customer...customers) {
    if(customers == null){
      return;
    }
    for(Customer customer : customers){
      simpleJdbc.update(
        "insert into customer (id, name)"
        + " values (?, ?)",
        customer.getId(),
        customer.getName());
    }
  }
}
