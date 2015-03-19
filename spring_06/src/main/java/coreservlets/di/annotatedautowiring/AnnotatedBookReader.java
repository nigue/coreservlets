package coreservlets.di.annotatedautowiring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import coreservlets.Book;
import coreservlets.BookLibrary;

public class AnnotatedBookReader {

  @Autowired
  private BookLibrary bookLibrary;
  
  public AnnotatedBookReader(){
  }

  public AnnotatedBookReader(BookLibrary bookLibrary) {
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