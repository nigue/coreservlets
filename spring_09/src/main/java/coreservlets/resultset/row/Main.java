package coreservlets.resultset.row;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;

public class Main {

    public static void main(String[] args) throws Exception {

        BeanFactory beanFactory
                = new ClassPathXmlApplicationContext(new String[]{
                    "spring/resultset/row/applicationContext.xml",
                    "spring/util/dataSourceContext.xml"});

        CustomerListQuery query
                = (CustomerListQuery) beanFactory.getBean("customerListQuery");

        List<Customer> customers = query.getCustomers();

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
