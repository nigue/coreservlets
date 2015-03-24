package coreservlets.aspectj.advice;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.CustomerReport;

public class Main {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
            "spring/aspectj/advice/coreservletsAopContext.xml",
            "spring/aspectj/advice/coreservletsContext.xml"});

        CustomerReport report = (CustomerReport) beanFactory.getBean("customerReport");

        report.getReport("Java Joe");

    }
}
