package coreservlets.demo03;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Book;
import coreservlets.BookReader;

public class Main {
  public static void main(String[] args) {

    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
        "spring/demo03/applicationContext.xml");

    BookReader client = (BookReader)beanFactory.getBean("bookReader");

    List<Book> books = client.read();

    System.out.printf("Client read: %s books%n", books.size());

  }
}
