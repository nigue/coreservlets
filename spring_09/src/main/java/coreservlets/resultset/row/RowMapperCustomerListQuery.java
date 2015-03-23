package coreservlets.resultset.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;

public class RowMapperCustomerListQuery implements CustomerListQuery {

    /**
     * JDBC template
     */
    private JdbcTemplate jdbc;

    public RowMapperCustomerListQuery(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getCustomers() {
        return this.jdbc.query("select id, name from customer", customerRowMapper);
    }

    private static final RowMapper customerRowMapper = new RowMapper() {
        @Override
        public Object mapRow(ResultSet rslt, int rowNum) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    };
}
