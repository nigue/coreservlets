package coreservlets;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class TypedListValues {
  private static final Logger log = Logger.getLogger(TypedListValues.class);

  public void setVarargsIntegers(Integer... values) {
    log.info("setVarargsIntegers: " + Arrays.toString(values));
  }

  public void setArrayFiles(File[] values) {
    log.info("setArrayFiles: " + Arrays.toString(values));
  }

  public void setListClasses(List<Class<? extends Object>> values) {
    log.info("setListClasses: " + values);
  }
}
