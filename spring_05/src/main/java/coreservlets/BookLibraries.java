package coreservlets;


public class BookLibraries {

  public static BookLibrary newInstance(){
    return new JavaBookLibrary();
  }
  
}
