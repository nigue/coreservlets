package coreservlets.noframework;

import java.util.List;

import coreservlets.Book;
import coreservlets.BookLibrary;
import coreservlets.BookReader;
import coreservlets.JavaBookLibrary;

public class Main {
  public static void main(String[] args) {

    BookLibrary service = new JavaBookLibrary();
    
    BookReader client = new BookReader(service);
    
    List<Book> books = client.read();

    System.out.printf("Client read: %s books%n", books.size());

  }
}


