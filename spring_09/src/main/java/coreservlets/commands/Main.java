package coreservlets.commands;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {

        BeanFactory beanFactory
                = new ClassPathXmlApplicationContext(new String[]{
                    "spring/commands/applicationContext.xml",
                    "spring/util/dataSourceContext.xml"});

        SpringDAO dao = (SpringDAO) beanFactory.getBean("springDao");

        dao.execute();

    }
}
