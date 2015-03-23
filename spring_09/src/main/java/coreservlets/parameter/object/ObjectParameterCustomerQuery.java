package coreservlets.parameter.object;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import coreservlets.Customer;
import coreservlets.CustomerQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ObjectParameterCustomerQuery implements CustomerQuery {

    private JdbcTemplate jdbc;

    public ObjectParameterCustomerQuery(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        try {
            return this.jdbc.queryForObject(
                    "select id, name from customer where name = ?",
                    customerRowMapper,
                    customerName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    };
}
