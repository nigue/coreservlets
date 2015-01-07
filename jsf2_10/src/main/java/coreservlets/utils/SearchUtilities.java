package coreservlets.utils;

import java.util.*;

/** Utility with static method to build a URL for any
 *  of the most popular search engines.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class SearchUtilities {
  private static SearchSpec[] commonSpecs =
    { new SearchSpec("Google",
                     "https://www.google.com/#q="),
      new SearchSpec("Yahoo",
                     "https://search.yahoo.com/search;?p="),
      new SearchSpec("Bing",
                     "http://www.bing.com/search?q="),
      new SearchSpec("GigaBlast",
                     "http://www.gigablast.com/search?q="),
      new SearchSpec("Baidu",
                     "http://www.baidu.com/s?wd="),
      new SearchSpec("DuckDuckGo",
                     "https://duckduckgo.com/?q="),
      new SearchSpec("Qwant",
                     "https://www.qwant.com/?q="),
    };
  
  private static List<String> searchEngineNames;
  
  static {
    searchEngineNames = new ArrayList<>();
    for(SearchSpec spec: commonSpecs) {
      searchEngineNames.add(spec.getName());
    }
  }
  
  public static SearchSpec[] commonSearchSpecs() {
    return(commonSpecs);
  }
  
  public static List<String> searchEngineNames() {
    return(searchEngineNames);
  }

  /** Given a search engine name and a search string, builds
   *  a URL for the results page of that search engine
   *  for that query. Returns null if the search engine name
   *  is not one of the ones it knows about.
   */
  
  public static String makeURL(String searchEngineName,
                               String searchString) {
    String searchURL = null;
    for(SearchSpec spec: commonSpecs) {
      if (spec.getName().equalsIgnoreCase(searchEngineName)) {
        searchURL = spec.makeURL(searchString);
        break;
      }
    }
    return(searchURL);
  }
  
  private SearchUtilities() {} // Uninstantiatable class
}
