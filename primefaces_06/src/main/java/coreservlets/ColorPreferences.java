package coreservlets;

import java.io.*;
import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class ColorPreferences implements Serializable {
  private static final long serialVersionUID = 1L;
  private String foreground="0000ff", background="fdf5e6";

  public String getForeground() {
    return(foreground);
  }

  public void setForeground(String foreground) {
    this.foreground = foreground;
  }

  public String getBackground() {
    return(background);
  }

  public void setBackground(String background) {
    this.background = background;
  }
  
  public String getStyle() {
    String style =
      String.format("color: #%s; background-color: #%s",
                    foreground, background);
    return(style);
  }
  
  public String showSample() {
    return("show-colors");
  }
}
