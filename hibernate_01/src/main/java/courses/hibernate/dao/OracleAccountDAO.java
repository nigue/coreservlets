package courses.hibernate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import courses.hibernate.vo.Account;

/**
 * Data Access Object for Account
 */
public class OracleAccountDAO extends AccountDAO {

    /**
     * @see courses.hibernate.dao.AccountDAO#createAccount()
     */
    public Account createAccount(Account account) {
        Connection connection = null;
        PreparedStatement getAccountIdStatement = null;
        PreparedStatement createAccountStatement = null;
        ResultSet resultSet = null;
        long accountId = 0;
        try {
            connection = getConnection();
            getAccountIdStatement = connection
                    .prepareStatement(OracleAccountDAOConstants.GET_ACCOUNT_ID);
            resultSet = getAccountIdStatement.executeQuery();
            resultSet.next();
            accountId = resultSet.getLong(1);

            createAccountStatement = connection
                    .prepareStatement(OracleAccountDAOConstants.CREATE_ACCOUNT);
            createAccountStatement.setLong(1, accountId);
            createAccountStatement.setString(2, account.getAccountType());
            createAccountStatement.setDouble(3, account.getBalance());
            createAccountStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            cleanupDatabaseResources(null, getAccountIdStatement, resultSet);
            cleanupDatabaseResources(connection, createAccountStatement, null);
        }
        return getAccount(accountId);
    }

    /**
     * @see courses.hibernate.dao.AccountDAO#getConnection()
     */
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(OracleAccountDAOConstants.URL);
        connection.setAutoCommit(false);
        return connection;
    }

}
