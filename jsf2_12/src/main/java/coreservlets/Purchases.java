package coreservlets;

import coreservlets.utils.Utils;
import java.util.*;
import javax.faces.bean.*;

@ManagedBean
public class Purchases {
  private String[] cheapItems =
    { "Gum", "Yo-yo", "Pencil" };
  private List<String> mediumItems =
    new ArrayList<>();
  private Map<String,String> valuableItems =
    new HashMap<>();
  private boolean isEverythingOK = true;

  public Purchases() {
    mediumItems.add("iPod");
    mediumItems.add("GameBoy");
    mediumItems.add("Cell Phone");
    valuableItems.put("low", "Lamborghini");
    valuableItems.put("medium", "Yacht");
    valuableItems.put("high", "JSF Training Course");
  }

  public String[] getCheapItems() {
    return(cheapItems);
  }

  public List<String> getMediumItems() {
    return(mediumItems);
  }

  public Map<String,String> getValuableItems() {
    return(valuableItems);
  }

  public String purchaseItems() {
    isEverythingOK = Utils.doBusinessLogic(this);
    isEverythingOK = Utils.doDataAccessLogic(this);
    if (isEverythingOK) {
      return("purchase-success");
    } else {
      return("purchase-failure");
    }
  }
}