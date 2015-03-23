package coreservlets.parameter.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import coreservlets.Customer;
import coreservlets.CustomerQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MapParameterCustomerQuery implements CustomerQuery {

    private JdbcTemplate jdbc;

    public MapParameterCustomerQuery(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        try {
            Map<String, Object> parameterMap = parameterize(customerName);
            return this.jdbc.queryForObject(
                    "select id, name from customer where name = ?",
                    customerRowMapper,
                    parameterMap.values().iterator().next());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Map<String, Object> parameterize(String customerName) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("customerName", customerName);
        return parameterMap;
    }

    private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    };
}
