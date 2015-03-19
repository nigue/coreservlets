package coreservlets.di.lookupmethod;

import java.util.HashSet;
import java.util.Set;

import coreservlets.BookLibrary;
import coreservlets.BookLibraryVisitor;

public abstract class AbstractBookLibraryVisitor
implements BookLibraryVisitor{
  
  public int visitLibrary(int visitCount){

    Set<Integer>set = new HashSet<Integer>();
    while(visitCount-- > 0){
      set.add(getBookLibrary().hashCode());
    }
    return set.size();
  }
   
 protected BookLibrary getBookLibrary(){return null;}
}
