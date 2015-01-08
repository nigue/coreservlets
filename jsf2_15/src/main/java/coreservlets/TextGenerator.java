package coreservlets;

import coreservlets.utils.RandomUtils;
import javax.faces.bean.*;

@ManagedBean
@ApplicationScoped
public class TextGenerator {
  private String[] colors =
    { "red", "yellow", "blue", "green" };
  private String[] phrases =
    { "Blah, blah, blah. ",
      "Yadda, yadda, yadda. ",
      "Foo, bar, baz. "
    };
  
  public String getRandomColor() {
    return(RandomUtils.randomElement(colors));
  }
  
  public String getRandomText() {
    int numPhrases = 1+ RandomUtils.randomInt(20);
    String text = "";
    for(int i=0; i<numPhrases; i++) {
      text += RandomUtils.randomElement(phrases);
    }
    return(text);
  }
  
  public String getStartCell() {
    String text = 
      String.format("<th bgcolor='%s'>",
                    getRandomColor());
    return(text);
  }
  
  public String getEndCell() {
    return("</th>");
  }
}
