package coreservlets.queryobject;

import static java.sql.Types.VARCHAR;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class QueryObjectCustomerQuery implements CustomerQuery {

    private MappingSqlQuery customerMappingSqlQuery;

    public QueryObjectCustomerQuery(final DataSource dataSource) {
        customerMappingSqlQuery = new CustomerMappingSqlQuery(dataSource);
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        try {
            return (Customer) this.customerMappingSqlQuery.findObject(customerName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class CustomerMappingSqlQuery extends MappingSqlQuery {

        public CustomerMappingSqlQuery(DataSource dataSource) {
            super(dataSource, "select id, name from customer where name = ?");
            super.setParameters(new SqlParameter[]{new SqlParameter(VARCHAR)});
        }

        @Override
        protected Object mapRow(ResultSet rslt, int rowNumber) throws SQLException {
            return new Customer(rslt.getString("id"), rslt.getString("name"));
        }
    }

}
