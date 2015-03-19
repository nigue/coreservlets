package coreservlets.di.propertysetter;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Book;
import coreservlets.BookReader;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory = 
      new ClassPathXmlApplicationContext(
        "spring/di/propertysetter/applicationContext.xml");
    BookReader reader = (BookReader) beanFactory.getBean("bookReader");
    List<Book>books = reader.read();
    System.out.printf("Client read: %s books%n", books.size());
  }
}
