package coreservlets;

import java.util.*;

public class ShapeList {
  private List<Shape> shapes;
  
  public ShapeList(Shape shape) {
    shapes = Arrays.asList(shape);
  }
  
  public ShapeList(List<Shape> shapes) {
    this.shapes = shapes;
  }

  public List<Shape> getShapes() {
    return shapes;
  }

  public void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }
  
  public double getTotalArea() {
    double total = 0.0;
    for(Shape shape: shapes) {
      total = total + shape.getArea();
    }
    return(total);
  }
  
  public Shape getSmallestShape() {
    Shape smallestShape = null;
    double smallestArea = Double.MAX_VALUE;
    for(Shape shape: shapes) {
      double area = shape.getArea();
      if (area < smallestArea) {
        smallestArea = area;
        smallestShape = shape;
      }
    }
    return(smallestShape);
  }

  public Shape getBiggestShape() {
    Shape biggestShape = null;
    double biggestArea = 0;
    for(Shape shape: shapes) {
      double area = shape.getArea();
      if (area > biggestArea) {
        biggestArea = area;
        biggestShape = shape;
      }
    }
    return(biggestShape);
  }
  
  public void printInfo() {
    System.out.printf("ShapeList has %s entries%n", 
                      shapes.size());
    System.out.printf("  Smallest: ");
    getSmallestShape().printInfo();
    System.out.printf("  Biggest: ");
    getBiggestShape().printInfo();
    System.out.printf("  Total area: %,.2f%n", 
                      getTotalArea());
  }
}
