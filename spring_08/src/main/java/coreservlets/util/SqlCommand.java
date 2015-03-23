package coreservlets.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class SqlCommand {

  private String sql;

  private boolean ignoreError;
  
  public SqlCommand(){
  }
  
  public SqlCommand(String sql){
    this.sql = sql;
  }
  
  public SqlCommand setSql(String sql){
    this.sql = sql;
    return this;
  }
    
  public SqlCommand setIgnoreError(boolean ignoreError){
    this.ignoreError = ignoreError;
    return this;
  }
  
  public static List<SqlCommand> parseResourcePath (String resourcePath)
      throws IOException{
    return parse(SqlCommand.class.getResourceAsStream(resourcePath));
  }
  
  public void execute(DataSource dataSource)throws SQLException{
    if(this.sql == null){
      throw new IllegalStateException("Error: SQL command. No value has been set.");
    }
    Connection conn = null;
    Statement stmt = null;
    boolean autoCommit = true;
    try{
      conn = dataSource.getConnection();
      autoCommit = conn.getAutoCommit();
      conn.setAutoCommit(true);
      stmt = conn.createStatement();
      stmt.execute(this.sql);
    }
    catch(SQLException e){
      if(this.ignoreError){
        return;
      }
      throw e;
    }
    catch(RuntimeException e){
      if(this.ignoreError){
        return;
      }
      throw e;      
    }
    finally{
      if(stmt != null){
        try{ stmt.close(); stmt = null; }catch(Exception ignore){}
      }
      if(conn != null){
        try{ conn.setAutoCommit(autoCommit); }catch(Exception ignore){}
        try{ conn.close(); conn = null; }catch(Exception ignore){}
      }
    }
  }

  public static List<SqlCommand> parse(InputStream source) throws IOException{
    BufferedReader reader = null;
    try{
      List<SqlCommand>commands = new ArrayList<SqlCommand>();
      String buffer=null;
      String line;
      reader = new BufferedReader(new InputStreamReader(source));
      while((line = reader.readLine())!=null){
        int index;
        if(line.trim().length() == 0){
          continue;
        }
        while((index = line.indexOf(';'))>-1){
          buffer = buffer != null 
            ? buffer + line.substring(0, index) 
            : line.substring(0, index);
          String trimmed = buffer.trim();
          if(trimmed.length() > 0){
            commands.add(new SqlCommand(trimmed));
          }
          buffer = null;
          line = line.length() > (index+1)
            ? line.substring(index + 1) + "\n" : "";
        }
        buffer = buffer != null ? buffer + "\n" + line : line;
      }
      return commands;
    }
    finally{
      if(reader != null){
        try{ 
          reader.close();
        }catch(Exception suppress){}
      }
    }
  }
  
  public String toString(){
    return this.sql;
  }
}
