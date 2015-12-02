package courses.hibernate.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.CdAccount;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBillerRegistration;
import courses.hibernate.vo.SavingsAccount;
import org.hibernate.type.DateType;

/**
 * Data Access Object for Account
 */
public class AccountDAO {

    /**
     * Create a new account or update an existing one
     *
     * @param account account to be persisted
     */
    public void saveOrUpdateAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(account);
    }

    /**
     * Retrieve an account from the data store
     *
     * @param accountId identifier of the account to be retrieved
     * @return account represented by the identifier provided
     */
    public Account getAccount(long accountId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Account account = (Account) session.get(Account.class, accountId);
        return account;
    }

    /**
     * Delete account from data store
     *
     * @param account account to be deleted
     */
    public void deleteAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(account);
    }

    /**
     * Save an ebillerRegistraton (ebiller-account relationship)
     *
     * @param ebillerRegistration ebillerRegistration to be saved
     */
    public void registerEBiller(EBillerRegistration ebillerRegistration) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(ebillerRegistration);
    }

    /**
     * Delete an ebillerRegistration (ebiller-account relationship)
     *
     * @param ebillerRegistration ebillerRegistration to be deleted
     */
    public void unregisterEBiller(EBillerRegistration ebillerRegistration) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(ebillerRegistration);
    }

    /*
     * Get all accounts in system
     */
    @SuppressWarnings("unchecked")
    public List<Account> getAccounts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<Account>) session.createQuery(
                "from Account order by balance asc").list();
    }

    /*
     * Get all checking accounts in system
     */
    @SuppressWarnings("unchecked")
    public List<CheckingAccount> getCheckingAccounts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<CheckingAccount>) session.createQuery(
                "from CheckingAccount order by balance asc").list();
    }

    @SuppressWarnings("unchecked")
    /*
     * Get all savings accounts in system
     */
    public List<SavingsAccount> getSavingsAccounts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<SavingsAccount>) session.createQuery(
                "from SavingsAccount order by balance asc").list();
    }

    /**
     * Get all CD accounts in system
     */
    @SuppressWarnings("unchecked")
    public List<CdAccount> getCdAccounts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<CdAccount>) session.createQuery(
                "from CdAccount order by balance asc").list();
    }

    /**
     * Get all accounts that were created after minCreationDate and which have a
     * balance greater than minBalance
     *
     * @param minBalance minBalance of accounts
     * @param minCreationDate minCreationDate of accounts
     * @return accounts that were created after minCreationDate and which have a
     * balance greater than minBalance
     */
    @SuppressWarnings("unchecked")
    public List<Account> getAccountsUsingNameBasedParameters(long minBalance, Date minCreationDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String query = "from Account a where a.balance > :someBalance and a.creationDate > :someDate";
        return (List<Account>) session.createQuery(query).setDate("someDate", minCreationDate).setLong("someBalance", minBalance).list();
    }

    /**
     * Get all accounts that were created after minCreationDate and which have a
     * balance greater than minBalance
     *
     * @param minBalance minBalance of accounts
     * @param minCreationDate minCreationDate of accounts
     * @return accounts that were created after minCreationDate and which have a
     * balance greater than minBalance
     */
    @SuppressWarnings("unchecked")
    public List<Account> getAccountsUsingGenericParameters(double minBalance, Date minCreationDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String query = "from Account a where a.balance > :someBalance and a.creationDate > :someDate";
        return (List<Account>) session.createQuery(query).setParameter("someDate", minCreationDate, DateType.INSTANCE).setParameter("someBalance", minBalance).list();
    }

    /**
     * Get all accounts in system and page the results
     */
    @SuppressWarnings("unchecked")
    public List<Account> getAccountsAndPage(int pageNumber, int resultsPerPage) {
        int startingIndex = ((pageNumber - 1) * resultsPerPage);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<Account>) session.createQuery(
                "from Account order by balance asc").setFirstResult(
                        startingIndex).setMaxResults(resultsPerPage).setFetchSize(
                        resultsPerPage).setTimeout(30).setComment(
                        "Retrieving page: " + pageNumber).list();
    }

    /**
     * Get map of accountIds and balances - specifying columns in query example
     *
     * @return map of accountIds and balances
     */
    @SuppressWarnings("unchecked")
    public Map<Long, Double> getAccountIdBalanceMap() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Object[]> accountIdBalanceArray = (List<Object[]>) session
                .createQuery("select accountId, balance from Account").list();

		// Iterate through list of results, where each result is an object array
        // representing one row of data
        // ---------------------------------------------------------------------
        Map<Long, Double> accountIdBalanceMap = new HashMap<Long, Double>();
        for (Object[] singleRowValues : accountIdBalanceArray) {
            long accountId = (Long) singleRowValues[0];
            double balance = (Double) singleRowValues[1];
            accountIdBalanceMap.put(accountId, balance);
        }
        return accountIdBalanceMap;
    }

    /**
     * Get min, max, and average account balance across all accounts
     *
     * @return min, max, and average account balance across all accounts
     */
    @SuppressWarnings("unchecked")
    public Map<String, Double> getAccountBalanceStatistics() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Object[]> listOfRowValues = (List<Object[]>) session.createQuery(
                "select min(a.balance), max(a.balance),"
                + "avg(a.balance) from Account a").list();

        Map<String, Double> accountStatistics = new HashMap<String, Double>();
        for (Object[] singleRowValues : listOfRowValues) {
            double min = (Double) singleRowValues[0];
            double max = (Double) singleRowValues[1];
            double avg = (Double) singleRowValues[2];

            accountStatistics.put("MIN", min);
            accountStatistics.put("MAX", max);
            accountStatistics.put("AVG", avg);
        }
        return accountStatistics;
    }

    /**
     * Get map of accountIds and average transaction amounts -- example of using
     * group by
     *
     * @return map of accountIds and average transaction amounts
     */
    @SuppressWarnings("unchecked")
    public Map<Long, Double> getAverageTransactionAmountByAccountId() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Object[]> listOfRowValues = (List<Object[]>) session
                .createQuery(
                        "select atx.account.accountId, avg(atx.amount)"
                        + " from AccountTransaction atx group by atx.account.accountId")
                .list();

        Map<Long, Double> accountIdAverageTransactionMap = new HashMap<Long, Double>();
        for (Object[] singleRowValues : listOfRowValues) {
            long accountId = (Long) singleRowValues[0];
            double average = (Double) singleRowValues[1];
            accountIdAverageTransactionMap.put(accountId, average);
        }
        return accountIdAverageTransactionMap;
    }

    /**
     * Get map of accountIds and average transaction amounts for accounts with
     * more than minTransaction transactions -- example of using group by and
     * having
     *
     * @return map of accountIds and average transaction amounts for accounts
     * with more than 20 transactions
     */
    @SuppressWarnings("unchecked")
    public Map<Long, Double> getAverageTransactionAmountByAccountId(
            long minTransactions) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Object[]> listOfRowValues = (List<Object[]>) session
                .createQuery(
                        "select atx.account.accountId, avg(atx.amount)"
                        + " from AccountTransaction atx group by atx.account.accountId"
                        + " having count(atx) >= " + minTransactions)
                .list();

        Map<Long, Double> accountIdAverageTransactionMap = new HashMap<Long, Double>();
        for (Object[] singleRowValues : listOfRowValues) {
            long accountId = (Long) singleRowValues[0];
            double average = (Double) singleRowValues[1];
            accountIdAverageTransactionMap.put(accountId, average);
        }
        return accountIdAverageTransactionMap;
    }

}
