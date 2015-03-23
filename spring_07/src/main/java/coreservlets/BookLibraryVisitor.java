package coreservlets;

public interface BookLibraryVisitor {
  
  /**
   * Visits the library per the <tt>visitCount</tt> parameter and returns
   * a value specifying the quantity of unique and visited libraries.
   * 
   * @param visitCount a non-negative integer
   * @return a non-negative integer
   */
  public int visitLibrary(int visitCount);

}
