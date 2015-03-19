package coreservlets.customframework;

import java.util.List;

import coreservlets.Book;
import coreservlets.BookReader;

public class Main {
  public static void main(String[] args) {
    
    ServiceProviderFramework framework =
      new ServiceProviderFramework();

    BookReader client = framework.getBookReaderInstance();
    
    List<Book> books = client.read();

    System.out.printf("Client read: %s books%n", books.size());
  }
}


