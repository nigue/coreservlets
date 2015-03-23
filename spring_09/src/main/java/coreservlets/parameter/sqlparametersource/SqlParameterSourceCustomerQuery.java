package coreservlets.parameter.sqlparametersource;

import static java.sql.Types.VARCHAR;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import coreservlets.Customer;
import coreservlets.CustomerQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SqlParameterSourceCustomerQuery implements CustomerQuery {

    private JdbcTemplate jdbc;

    public SqlParameterSourceCustomerQuery(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        try {
            SqlParameterSource parameterMap = parameterize(customerName);
            return this.jdbc.queryForObject(
                    "select id, name from customer where name = ?",
                    customerRowMapper,
                    parameterMap.getValue("customerName"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private SqlParameterSource parameterize(String customerName) {

        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("customerName", customerName, VARCHAR);
        return parameterMap;
    }

    private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rslt, int rowNum) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    };
}
