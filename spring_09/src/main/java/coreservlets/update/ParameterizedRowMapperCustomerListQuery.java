package coreservlets.update;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ParameterizedRowMapperCustomerListQuery
        implements CustomerListQuery {

    private JdbcTemplate simpleJdbc;

    public ParameterizedRowMapperCustomerListQuery(DataSource dataSource) {
        this.simpleJdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Customer> getCustomers() {
        return this.simpleJdbc.<Customer>query(
                "select id, name from customer", customerRowMapper);
    }

    private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    };
}
