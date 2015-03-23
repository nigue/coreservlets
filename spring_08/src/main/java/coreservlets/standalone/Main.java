package coreservlets.standalone;

import coreservlets.Customer;
import coreservlets.CustomerQuery;
import coreservlets.SpringJdbcCustomerQuery;
import coreservlets.util.EmbeddedDerbyDataSource;

public class Main {

    public static void main(String[] args) throws Exception {
        EmbeddedDerbyDataSource dataSource = new EmbeddedDerbyDataSource("target/ngcdb", "/setup.sql");
        CustomerQuery query = new SpringJdbcCustomerQuery(dataSource);
        Customer customer = query.getCustomerByName("Java Joe");
        System.out.println(customer);
    }

}
