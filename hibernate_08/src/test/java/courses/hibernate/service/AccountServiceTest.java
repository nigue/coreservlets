package courses.hibernate.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest extends ServiceTest {

    /**
     * Test getAccounts
     */
//    @Test
    public void testGetAccounts() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Account> accounts = accountService.getAccounts();

            TestCase.assertEquals(5, accounts.size());
            // Confirm data is sorted correctly
            // ------- ---- -- ------ ---------
            double previousAccountBalance = 0.0;
            for (Account account : accounts) {
                TestCase
                        .assertTrue(account.getBalance() >= previousAccountBalance);
                previousAccountBalance = account.getBalance();
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getCheckingAccounts
     */
//    @Test
    public void testGetCheckingAccounts() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<CheckingAccount> accounts = accountService
                    .getCheckingAccounts();

            TestCase.assertEquals(4, accounts.size());
            // Confirm data is sorted correctly
            // ------- ---- -- ------ ---------
            double previousAccountBalance = 0.0;
            for (Account account : accounts) {
                TestCase
                        .assertTrue(account.getBalance() >= previousAccountBalance);
                previousAccountBalance = account.getBalance();
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getSavingsAccounts
     */
//    @Test
    public void testGetSavingsAccounts() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<SavingsAccount> accounts = accountService.getSavingsAccounts();

            TestCase.assertEquals(1, accounts.size());
            // Confirm data is sorted correctly
            // ------- ---- -- ------ ---------
            double previousAccountBalance = 0.0;
            for (Account account : accounts) {
                TestCase
                        .assertTrue(account.getBalance() >= previousAccountBalance);
                previousAccountBalance = account.getBalance();
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountsUsingNameBasedParameters
     */
//    @Test
    public void testGetAccountsUsingNameBasedParameters() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Date currentDate = new Date();
            List<Account> accounts = accountService.getAccountsUsingNameBasedParameters(3000, currentDate);
            TestCase.assertEquals(2, accounts.size());
            for (Account account : accounts) {
                TestCase.assertTrue(account.getBalance() > 3000);
                TestCase.assertTrue(account.getCreationDate().getTime() > currentDate.getTime());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountsUsingGenericParameters
     */
//    @Test
    public void testGetAccountsUsingGenericParameters() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Date currentDate = new Date();
            List<Account> accounts = accountService
                    .getAccountsUsingGenericParameters(3000.00, currentDate);
            TestCase.assertEquals(2, accounts.size());
            for (Account account : accounts) {
                TestCase.assertTrue(account.getBalance() > 3000);
                TestCase
                        .assertTrue(account.getCreationDate().getTime() > currentDate
                                .getTime());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test testGetAccountsAndPage
     */
//    @Test
    public void testGetAccountsAndPage() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            // 2 results per page, confirm first page has 2 records
            // - ------- --- ----- ------- ----- ---- --- - -------
            List<Account> accounts = accountService.getAccountsAndPage(1, 2);
            TestCase.assertEquals(2, accounts.size());

            // 2 results per page, confirm second page has 2 records
            // - ------- --- ----- ------- ------ ---- --- - -------
            accounts = accountService.getAccountsAndPage(2, 2);
            TestCase.assertEquals(2, accounts.size());

            // 2 results per page, confirm third page has 1 record
            // - ------- --- ----- ------- ----- ---- --- - ------
            accounts = accountService.getAccountsAndPage(3, 2);
            TestCase.assertEquals(1, accounts.size());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountIdBalanceMap
     */
//    @Test
    public void testGetAccountIdBalanceMap() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<Long, Double> accountIdBalanceMap = accountService
                    .getAccountIdBalanceMap();
            TestCase.assertEquals(5, accountIdBalanceMap.size());
            for (Long accountId : accountIdBalanceMap.keySet()) {
                TestCase.assertTrue(accountId > 0);
                TestCase.assertTrue(accountIdBalanceMap.get(accountId) > 0);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountBalanceStatistics
     */
//    @Test
    public void testGetAccountBalanceStatistics() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<String, Double> accountStatistics = accountService
                    .getAccountBalanceStatistics();
            TestCase.assertEquals(3, accountStatistics.size());

            TestCase.assertEquals(1000.00, accountStatistics.get("MIN"));
            TestCase.assertEquals(5000.00, accountStatistics.get("MAX"));
            TestCase.assertEquals(3000.00, accountStatistics.get("AVG"));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAverageTransactionAmountByAccountId
     */
//    @Test
    public void testGetAverageTransactionAmountByAccountId() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<Long, Double> accountIdAverageTransactionMap = accountService
                    .getAverageTransactionAmountByAccountId();
            TestCase.assertEquals(4, accountIdAverageTransactionMap.size());
            for (Long accountId : accountIdAverageTransactionMap.keySet()) {
                TestCase.assertTrue(accountId > 0);
                TestCase
                        .assertTrue(accountIdAverageTransactionMap
                                .get(accountId) > 0);
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAverageTransactionAmountByAccountIdUsingHaving
     */
//    @Test
    public void testGetAverageTransactionAmountByAccountIdUsingHaving() {
        Session session = null;
        AccountService accountService = new AccountService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<Long, Double> accountIdAverageTransactionMap = accountService
                    .getAverageTransactionAmountByAccountId(2);
            TestCase.assertEquals(3, accountIdAverageTransactionMap.size());
            for (Long accountId : accountIdAverageTransactionMap.keySet()) {
                TestCase.assertTrue(accountId > 0);
                TestCase
                        .assertTrue(accountIdAverageTransactionMap
                                .get(accountId) > 0);
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

}
