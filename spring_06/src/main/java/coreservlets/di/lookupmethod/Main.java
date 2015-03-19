package coreservlets.di.lookupmethod;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.BookLibraryVisitor;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
      "spring/di/lookupmethod"
      + "/applicationContext.xml");

    BookLibraryVisitor visitor = (BookLibraryVisitor)
      beanFactory.getBean("bookLibraryVisitor");
    int uniqueLibraries = visitor.visitLibrary(100);
    
    System.out.printf("Visit library instruction: %s. Unique libraries: %s%n",
      100, uniqueLibraries);
    System.out.println("BookLibraryVisitor class: " + visitor.getClass().getName());
  }  
}
