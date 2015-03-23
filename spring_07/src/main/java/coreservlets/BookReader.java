package coreservlets;

import java.util.List;

public class BookReader {

  private BookLibrary bookLibrary;

  public BookReader() {
    super();
  }

  public BookReader(BookLibrary bookLibrary) {
    this.bookLibrary = bookLibrary;
  }

  public void setBookLibrary(BookLibrary bookLibrary) {
    this.bookLibrary = bookLibrary;
  }

  public List<Book> read() {
    List<Book> books = bookLibrary.search("Java");
    for(Book book : books){
      System.out.printf("Reading: %s%n", book);
    }
    return books;
  }
}