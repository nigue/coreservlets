package coreservlets;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class CollectionValues {
  private static final Logger log = Logger.getLogger(CollectionValues.class);

  public void setVarargsValues(String... values) {
    log.info("setVarargsValues: " + Arrays.toString(values));
  }

  public void setArrayValues(String[] values) {
    log.info("setArrayValues: " + Arrays.toString(values));
  }

  public void setListValues(List<String> values) {
    log.info("setListValues: " + values);
  }

  public void setSetValues(Set<String> values) {
    log.info("setSetValues: " + values);
  }

  public void setCollectionValues(Collection<String> values) {
    log.info("setListValues: " + values);
  }
}
