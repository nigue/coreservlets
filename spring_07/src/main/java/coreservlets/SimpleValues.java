package coreservlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SimpleValues {

  private static final Logger log = Logger.getLogger(SimpleValues.class);

  public enum Color{
    Red, Yellow, Blue;
  }

  public void setNumber(Number number) {
    log.info("setNumber: " + number);
  }
  
  public void setInteger(Integer integer) {
    log.info("setInteger: " + integer);
  }

  public void setColor(Color color) {
    log.info("setColor: " + color);
  }

  public void setFile(File file) {
    log.info("setFile: " + file);
  }
  
  public void setProperties(Properties properties) {
    log.info("setProperties: " + properties);
  }
  
  public void setInputStream(InputStream inputStream) throws IOException {
    log.info("setInputStream (\"true\" if not null): " + (inputStream != null));
    if(inputStream != null){
      inputStream.close();
    }
  }
}
