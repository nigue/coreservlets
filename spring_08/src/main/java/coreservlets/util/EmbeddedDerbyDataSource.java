package coreservlets.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * DataSource implementation for an embedded Derby database instance.
 */
public class EmbeddedDerbyDataSource extends DriverManagerDataSource{
  
  private static final Logger log =
    Logger.getLogger(EmbeddedDerbyDataSource.class);
    
  /**
   * Creates a new EmbeddedDerbyDataSource instance.
   * <p>
   * Initializes the instance with a custom database name and optionally
   * a list of setup scripts to be sourced from the class path and executed.
   * 
   * @param databaseName required
   * @param scriptPaths optional
   * @throws IllegalArgumentException for parameter spec violations
   */
  public EmbeddedDerbyDataSource(
      final String databaseName, String...scriptPaths) {
    // prerequisites
    if(databaseName == null){
      throw new IllegalArgumentException("Required: database name.");
    }
    
    // configure DataSource
    super.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
    super.setUrl(MessageFormat.format("jdbc:derby:{0}", databaseName));
    
    // init Derby
    String initCmd = 
      MessageFormat.format("jdbc:derby:{0};create=true", databaseName);
    try{
      DriverManager.getConnection(initCmd);
      log.info("Derby DB initialized");
    }
    catch(SQLException e){
      throw new IllegalStateException("Error: DB init."
        + " Failed to start database using command: " + initCmd, e);
    }
    
    // init tables and seed data
    if(scriptPaths != null){
      for(String scriptPath : scriptPaths){
        List<SqlCommand> commands = null;
        try{
          commands = SqlCommand.parseResourcePath(scriptPath);
        }
        catch(IOException e){
          throw new IllegalStateException("Error: I/O for SQL scripts."
            + " Failed to load: " + scriptPath, e);
        }
        for(SqlCommand sqlCmd : commands){
          try{
            sqlCmd.setIgnoreError(true).execute(this);
          }
          catch(SQLException e){
            log.warn("Error: setup SQL. Failed to execute: " + sqlCmd, e);
          }
        }
      }
    }
   
    // shutdown
    Runtime.getRuntime().addShutdownHook(new Thread(){
      @Override
      public void run() {
        String shutdownCmd =
          MessageFormat.format("jdbc:derby:{0};shutdown=true", databaseName);
        try{
          DriverManager.getConnection(shutdownCmd);
          throw new IllegalStateException("Error: DB shutdown."
            + " Failed to shutdown database using command: " + shutdownCmd);
        }
        catch(SQLException e){
          log.info("Derby DB shutdown");
          // oddly, the driver throws a SQLException to verify shutdown.
          // therefore, anticipate an error rather than normal execution
        }
      }
    });
  }  
}
