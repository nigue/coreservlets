package coreservlets.update;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import coreservlets.Customer;
import coreservlets.CustomerUpdate;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringCustomerUpdate implements CustomerUpdate {

    private JdbcTemplate simpleJdbc;

    public SpringCustomerUpdate(DataSource dataSource) {
        this.simpleJdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Customer customer) {
        Map<String, Object> parameters = parameterize(customer);

        boolean updated = 0 < simpleJdbc.update(
                "update customer set name = ? where id = ?",
                parameters);

        if (updated) {
            return;
        }

        simpleJdbc.update(
                "insert into customer (id, name) values (?, ?)",
                parameters);
    }

    @Override
    public void deleteById(String customerId) {
        simpleJdbc.update("delete from customer where id = ?", customerId);
    }

    private static Map<String, Object> parameterize(Customer customer) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("customerId", customer.getId());
        parameterMap.put("customerName", customer.getName());
        return parameterMap;
    }

}
