package coreservlets.customframework;

import coreservlets.BookReader;
import coreservlets.JavaBookLibrary;

public class ServiceProviderFramework {

  private BookReader bookReader;
  
  public ServiceProviderFramework(){
    this.bookReader = new BookReader(new JavaBookLibrary());    
  }
  
  public BookReader getBookReaderInstance(){
    return this.bookReader;
  }
}
