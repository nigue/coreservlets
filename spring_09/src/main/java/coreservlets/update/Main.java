package coreservlets.update;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;
import coreservlets.CustomerUpdate;

public class Main {

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory
                = new ClassPathXmlApplicationContext(new String[]{
                    "spring/update/applicationContext.xml",
                    "spring/util/dataSourceContext.xml"});

        // acquire beans
        CustomerUpdate customerUpdate
                = (CustomerUpdate) beanFactory.getBean("customerUpdate");
        CustomerListQuery customerQuery
                = (CustomerListQuery) beanFactory.getBean("customerListQuery");

        // setup value object
        Customer customer = new Customer();
        customer.setId("jspring");
        customer.setName("Joe Spring");

        // before save
        System.out.println("Before initial save: " + customerQuery.getCustomers());

        // initial save
        customerUpdate.save(customer);
        System.out.println("After initial save : " + customerQuery.getCustomers());

        // modify customer and save again
        customer.setName("Joseph Spring");
        customerUpdate.save(customer);
        System.out.println("After update       : " + customerQuery.getCustomers());

        // delete
        customerUpdate.deleteById(customer.getId());
        System.out.println("After delete       : " + customerQuery.getCustomers());
    }
}
