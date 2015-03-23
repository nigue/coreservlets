package coreservlets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Spring JDBC API query demonstration.
 */
public class SpringJdbcCustomerQuery implements CustomerQuery {

    private JdbcTemplate jdbc;

    public SpringJdbcCustomerQuery(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        try {
            return this.jdbc.queryForObject(
                    "select id, name from customer where name = ?", customerRowMapper, customerName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Customer> getCustomers() {
        return this.jdbc.<Customer>query(
                "select id, name from customer",
                customerRowMapper);
    }

    private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    };
}
