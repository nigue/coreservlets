package coreservlets.tx;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Customer;
import coreservlets.persistence.CustomerBatchPersistence;

public class Main {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
            "spring/persistence/coreservletsPersistenceContext.xml",
            "spring/tx/coreservletsTxContext.xml",
            "spring/datasource/coreservletsDataSourceContext.xml"});

        CustomerBatchPersistence dao
                = (CustomerBatchPersistence) beanFactory.getBean("customerBatchPersistence");

        int count = dao.getCustomerCount();

        try {
            dao.insert(
                    new Customer("dup-id", "dup-name"),
                    new Customer("dup-id", "dup-name"));
            throw new IllegalStateException("Failed. assertion."
                    + " Expected an error inserting duplicate records.");
        } catch (Exception expected) {
            boolean rowCountModified = count != dao.getCustomerCount();
            System.out.printf("Row count changed? %s%n", rowCountModified);
            if (rowCountModified) {
                throw new IllegalStateException("Failed. assertion."
                        + " Rollback failed.");
            }
        }
    }
}
