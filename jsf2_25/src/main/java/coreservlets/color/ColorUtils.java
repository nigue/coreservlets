package coreservlets.color;

import coreservlets.utils.RandomUtils;
import javax.faces.bean.*;

@ManagedBean
@ApplicationScoped
public class ColorUtils {
  private String[] foregrounds = {
    "DarkBlue", "DarkCyan", "DarkGoldenRod", "DarkGray", 
    "DarkGreen", "DarkKhaki", "DarkMagenta", "DarkOliveGreen", 
    "Darkorange", "DarkOrchid", "DarkRed", "DarkSalmon", 
    "DarkSeaGreen", "DarkSlateBlue", "DarkSlateGray", 
    "DarkTurquoise", "DarkViolet"  
  };
  private String[] backgrounds = {
    "LightBlue", "LightCyan", "LightGoldenRodYellow", 
    "LightGrey", "LightGreen", "LightPink", "LightSalmon",
    "LightSeaGreen", "LightSkyBlue", "LightSlateGray ", 
    "LightSteelBlue", "LightYellow"
  };

  /** One of the foreground color names, at random.
     * @return  */
  
  public String getRandomForeground() {
    return(RandomUtils.randomElement(foregrounds));
  }
  
  /** One of the background color names, at random.
     * @return  */
  
  public String getRandomBackground() {
    return(RandomUtils.randomElement(backgrounds));
  }
}
