package coreservlets.resultset.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;

public class ResultSetExtractorCustomerListQuery implements CustomerListQuery {

    private JdbcTemplate jdbc;

    public ResultSetExtractorCustomerListQuery(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getCustomers() {
        return (List) this.jdbc.query("select id, name from customer", customerListExtractor);
    }

    private static final ResultSetExtractor customerListExtractor = new ResultSetExtractor() {
        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            List<Customer> list = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("id"), rs.getString("name"));
                list.add(customer);
            }
            return list;
        }
    };
}
