package coreservlets.utils;

import java.util.*;

public class LanguageUtils {
  private static String[] languages =
    { "Java", "JavaScript", "C#", "C++", "PHP", "Python", "Perl", "Ruby", "Scala" };
  
  public static String findMostPopularLanguage(int index) {
    int numLanguages = languages.length;
    if (index >= numLanguages) {
      index = numLanguages - 1;
    }
    return(languages[index]);
  }
  
  public static String[] languageArray() {
    return(languages);
  }
  
  // Could have bad performance unless you are sure that the number of languages
  // you ask for is substantially smaller than the number of choices.
  
  public static List<String> randomLanguages(int numLanguages) {
    if (numLanguages >= languages.length) {
      return(Arrays.asList(languages));
    } else {
      List<String> randomLanguages = new ArrayList<>();
      for(int i=0; i<numLanguages; i++) {
        randomLanguages.add(uniqueRandomLanguage(randomLanguages, languages));
      }
      return(randomLanguages);
    }
  }
  
  private static String uniqueRandomLanguage(List<String> previousLanguages,
                                             String[] newLanguageChoices) {
    String newLanguage = RandomUtils.randomElement(newLanguageChoices);
    if (!previousLanguages.contains(newLanguage)) {
      return(newLanguage);
    } else {
      return(uniqueRandomLanguage(previousLanguages, newLanguageChoices));
    }
  }
  
  private LanguageUtils() {}; // Uninstantiatable class
}
