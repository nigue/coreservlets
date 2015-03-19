package coreservlets;


public class BookLibraryFactory {

  public BookLibrary newInstance(){
    return new JavaBookLibrary();
  }
  
}
