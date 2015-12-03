package courses.hibernate.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.AfterClass;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest extends ServiceTest {

	@AfterClass
	public static void closeHibernateSessionFactory() {
		HibernateUtil.getSessionFactory().close();
	}

	/**
	 * Test account creation
	 */
	//@Test
	public void testCreateAccount() {

		SavingsAccount account = new SavingsAccount();
		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			account.setBalance(1000L);
			account.setInterestRate(3.5);

			TestCase.assertTrue(account.getAccountId() == 0);

			// save the account
			// ---- --- -------
			accountService.saveOrUpdateAccount(account);

			session.getTransaction().commit();
			System.out.println("var account = " + account);

			// check that IDs were set after the hbm session
			// ----- ---- --- ---- --- ----- --- --- -------
			TestCase.assertTrue(account.getAccountId() > 0);

			// check that interceptor set creation date
			// ----- ---- ----------- --- -------- ----
			TestCase.assertNotNull(account.getCreationDate());
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		// cleanup
		// -------
		deleteAccount(account);
	}

	/**
	 * Test retrieval of account
	 */
	//@Test
	public void testGetAccount() {
		Account account = null;
		Session session = null;

		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			account = createSavingsAccount();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			System.out.println("var account = " + account);
			AccountService accountService = new AccountService();
			Account anotherCopy = accountService.getAccount(account
					.getAccountId());

			System.out.println("var anotherCopy = " + anotherCopy);

			// make sure these are two separate instances
			// ---- ---- ----- --- --- -------- ---------
			TestCase.assertTrue(account != anotherCopy);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		// cleanup
		// -------
		deleteAccount(account);
	}

	/**
	 * Test updating of account balance
	 */
	//@Test
	public void testUpdateAccountBalance() {

		Account account = null;
		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			account = createCheckingAccount();
			System.out.println("var account = " + account);

			// Confirm creation date is not null and updateDate is null
			// ------- -------- ---- -- --- ---- --- ---------- -- ----
			TestCase.assertNotNull(account.getCreationDate());
			TestCase.assertNull(account.getUpdateDate());

			account.setBalance(2000);
			accountService.saveOrUpdateAccount(account);

			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Account anotherCopy = accountService.getAccount(account
					.getAccountId());
			System.out.println("var anotherCopy = " + anotherCopy);

			// make sure the one we just pulled back
			// from the database has the updated balance
			// -----------------------------------------
			TestCase.assertTrue(anotherCopy.getBalance() == 2000);

			// Confirm both dates are not null
			// ------- ---- ----- --- --- ----
			TestCase.assertNotNull(anotherCopy.getCreationDate());
			TestCase.assertNotNull(anotherCopy.getUpdateDate());

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
		// cleanup
		// -------
		deleteAccount(account);
	}

	/**
	 * Test deletion of account
	 */
	//@Test
	public void testDeleteAccount() {

		Account account = null;
		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			account = createCheckingAccount();
			System.out.println("var account = " + account);

			// delete the account
			// ------ --- -------
			accountService.deleteAccount(account);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
		try {
			// try to get the account again -- should be null
			// --- -- --- --- ------- ----- -- ------ -- ----
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Account anotherCopy = accountService.getAccount(account
					.getAccountId());

			System.out.println("var anotherCopy = " + anotherCopy);

			TestCase.assertNull(anotherCopy);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test add interest to savings accounts - batch update test
	 */
	//@Test
	public void testAddInterestToSavingsAccounts() {

		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			List<SavingsAccount> savingsAccounts = accountService
					.getSavingsAccounts();
			int numberOfSavingsAccounts = savingsAccounts.size();

			int numberOfAccountsUpdated = accountService
					.addInterestToSavingsAccounts();

			TestCase.assertEquals(numberOfSavingsAccounts,
					numberOfAccountsUpdated);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test add interest to savings accounts - batch update test
	 */
	//@Test
	public void testArchiveAccounts() {

		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			List<Account> accounts = accountService.getAccounts();
			int numberOfAccounts = accounts.size();

			int numberOfAccountsInserted = accountService.archiveAccounts();
			TestCase.assertEquals(numberOfAccounts, numberOfAccountsInserted);

			int numberOfAccountDeleted = accountService
					.deleteArchivedAccounts();
			TestCase.assertEquals(numberOfAccounts, numberOfAccountDeleted);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test creationDateFilter
	 */
	//@Test
	public void testCreationDateFilter() {

		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.enableFilter("creationDateFilter").setParameter("asOfDate",
					new Date());
			List<Account> accounts = accountService.getAccounts();
			TestCase.assertEquals(2, accounts.size());

			session.disableFilter("creationDateFilter");
			accounts = accountService.getAccounts();
			TestCase.assertEquals(5, accounts.size());

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test accountTransactionDateFilter
	 */
	//@Test
	public void testAccountTransactionDateFilter() {

		Session session = null;
		AccountService accountService = new AccountService();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -7);

			Account account = accountService.getAccount(4);
			session.enableFilter("transactionDateFilter").setParameter(
					"asOfDate", new Date(calendar.getTimeInMillis()));
			SortedSet<AccountTransaction> accountTransactions = account
					.getAccountTransactions();
			TestCase.assertEquals(2, accountTransactions.size());

			session.evict(account);

			session.disableFilter("transactionDateFilter");
			account = accountService.getAccount(4);
			SortedSet<AccountTransaction> accountTransactions2 = account
					.getAccountTransactions();
			TestCase.assertEquals(3, accountTransactions2.size());

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test Hibernate's Statistics features
	 */
	public static void main(String args[]) {
		Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
		stats.setStatisticsEnabled(true);

		AccountServiceTest testCases = new AccountServiceTest();
		testCases.testCreateAccount();
		testCases.testDeleteAccount();
		testCases.testGetAccount();
		testCases.testUpdateAccountBalance();

		stats.logSummary();

		EntityStatistics accountStats = stats
				.getEntityStatistics("courses.hibernate.vo.Account");
		System.out.println("Account Insert Count = "
				+ accountStats.getInsertCount());
	}

}
