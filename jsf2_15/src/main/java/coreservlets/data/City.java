package coreservlets.data;

public class City {
  private final String name, population;

  public City(String name, String population) {
    this.name = name;
    this.population = population;
  }

  public String getName() {
    return (name);
  }

  public String getPopulation() {
    return (population);
  }
}
