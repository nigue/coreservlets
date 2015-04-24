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
public class JavaDBAccountDAO extends AccountDAO {

    /**
     * @return 
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

            createAccountStatement = connection
                    .prepareStatement(JavaDBAccountDAOConstants.CREATE_ACCOUNT);
            createAccountStatement.setString(1, account.getAccountType());
            createAccountStatement.setDouble(2, account.getBalance());
            createAccountStatement.execute();

            connection.commit();

            getAccountIdStatement = connection
                    .prepareStatement(JavaDBAccountDAOConstants.GET_ACCOUNT_ID);
            resultSet = getAccountIdStatement.executeQuery();
            resultSet.next();
            accountId = resultSet.getLong(1);

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
     * @return 
     * @throws java.sql.SQLException
     * @see courses.hibernate.dao.AccountDAO#getConnection()
     */
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(JavaDBAccountDAOConstants.URL);
        connection.setAutoCommit(false);
        return connection;
    }

}
