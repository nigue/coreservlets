package coreservlets;

import java.util.Random;

import javax.faces.bean.*;

@ApplicationScoped
@ManagedBean
public class ImageBean {
  private int numImages = 22;
  private Random r = new Random();
  private String template =
    "cafe-%s.jpg";
  
  public String getRandomImage() {
    int num = r.nextInt(numImages) + 1;
    // The path will be used with "url" attribute of h:graphicImage.
    // So, path is relative to WebContent in Eclipse project (to project root when deployed).
    // If you want it relative to WebContent/resources, move "images" to resources and use
    // h:graphicImage name="cafe-x.jpg" library="images/internet-cafes"
    // h:graphicImage is discussed in the page templating section of
    // the general JSF2 tutorial (http://www.coreservlets.com/JSF-Tutorial/jsf2/).
    String path = String.format(template, num);
    return(path);
  }
}
