package coreservlets.advice.ordering;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.CustomerQuery;

public class Main {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
            "spring/advice/ordering/coreservletsAopContext.xml",
            "spring/coreservletsContext.xml"});

        CustomerQuery query = (CustomerQuery) beanFactory.getBean("customerQuery");

        query.getCustomerByName("Java Joe");

    }
}
