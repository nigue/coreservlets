package coreservlets.data;

import coreservlets.info.StateInfo;
import java.util.*;

public class State {
  private final String name;
  private final Map<String,String> cityMap = new LinkedHashMap<>();

  public State(String name, City...cities) {
    this.name = name;
    for(City c: cities) {
      cityMap.put(c.getName(), c.getPopulation());
    } 
    StateInfo.STATE_NAMES.add(name);
    StateInfo.STATE_MAP.put(name, cityMap);
  }

  public String getName() {
    return(name);
  }

  public Map<String, String> getCityMap() {
    return(cityMap);
  }
}
