package coreservlets.info;

import coreservlets.data.City;
import coreservlets.data.State;
import java.util.*;

public class StateInfo {
  // Maps state name to a Map that has city names and associated populations
  public static final Map<String, Map<String,String>> STATE_MAP =
    new HashMap<>();
  public static final List<String> STATE_NAMES = new ArrayList<>();
  // Populations from http://www.citypopulation.de/USA.html
  public static final State[] STATES =
    { new State("Maryland", 
                new City("Baltimore", "622,104"),
                new City("Columbia", "105,000"),
                new City("Frederick", "66,893"),
                new City("Gaithersburg", "65,690")),
      new State("Virginia", 
                new City("Virginia Beach", "448,479"),
                new City("Norfolk", "246,139"),
                new City("Chesapeake", "230,571"),
                new City("Arlington", "224,906")),
      new State("Pennsylvania", 
                new City("Philadelphia", "1,553,165"),
                new City("Pittsburgh", "305,841"),
                new City("Allentown", "118,577"),
                new City("Erie", "100,671")),
      new State("New Jersey", 
                new City("Newark", "278,427"),
                new City("Jersey City", "257,342"),
                new City("Paterson", "145,948"),
                new City("Elizabeth", "127,558")),
      new State("New York", 
                new City("New York", "8,405,837"),
                new City("Buffalo", "258,959"),
                new City("Rochester", "210,358"),
                new City("Yonkers", "199,766"))     };
}
