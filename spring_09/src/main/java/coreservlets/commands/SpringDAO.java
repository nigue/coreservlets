package coreservlets.commands;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Spring JDBC API query demonstration.
 */
public class SpringDAO {

  /** Default JDBC template */
  private JdbcTemplate jdbc;

  public SpringDAO(DataSource dataSource) {
    this.jdbc = new JdbcTemplate(dataSource);
  }

  /**
   * Issues a command to the database. The absence of an error indicates
   * a successful transaction.
   */
  public void execute(){
    
    this.jdbc.execute("CALL SYSCS_UTIL.SYSCS_SET_STATISTICS_TIMING(0)");
    
  }
}
