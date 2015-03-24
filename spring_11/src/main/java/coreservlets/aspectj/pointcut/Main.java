package coreservlets.aspectj.pointcut;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.CustomerReport;

public class Main {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
            "spring/aspectj/pointcut/coreservletsAopContext.xml",
            "spring/aspectj/pointcut/coreservletsContext.xml"});

        CustomerReport report = (CustomerReport) beanFactory.getBean("customerReport");

        report.getReport("Java Joe");

    }
}
