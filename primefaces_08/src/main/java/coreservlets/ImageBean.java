package coreservlets;

import java.util.Random;

import javax.faces.bean.*;

@ApplicationScoped
@ManagedBean
public class ImageBean {
  private int numImages = 22;
  private Random r = new Random();
  
  public String getRandomImage() {
    int num = r.nextInt(numImages) + 1;
    // Path is relative to WebContent in Eclipse project
    // If you want it relative to WebContent/resources, move "images" to resources and use
    // h:graphicImage name="cafe-x.jpg" library="images/internet-cafes"
    String path = 
      String.format("cafe-%s.jpg", num);
    return(path);
  }
}
