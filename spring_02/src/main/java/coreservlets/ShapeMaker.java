package coreservlets;

public class ShapeMaker {
  public static Shape randomShape1() {
    return(randomShape2(10));
  }
  
  public static Shape randomShape2(double size) {
    double d = Math.random();
    if (d < 0.333) {
      return(new Circle(size));
    } else if (d < 0.666) {
      return(new Rectangle(size, size*2));
    } else {
      return(new RightTriangle(size, size*2));
    }
  }
}
