package courses.hibernate.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountEBillerSummary;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest extends ServiceTest {
	/**
	 * Test getAccounts
	 */
	@Test
	public void testGetAccounts() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Account> accounts = accountService.getAccounts();

			Assert.assertEquals(5, accounts.size());
			// Confirm data is sorted correctly
			// ------- ---- -- ------ ---------
			double previousAccountBalance = 0.0;
			for (Account account : accounts) {
				Assert
						.assertTrue(account.getBalance() >= previousAccountBalance);
				previousAccountBalance = account.getBalance();
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getCheckingAccounts
	 */
	@Test
	public void testGetCheckingAccounts() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<CheckingAccount> accounts = accountService
					.getCheckingAccounts();

			Assert.assertEquals(4, accounts.size());
			// Confirm data is sorted correctly
			// ------- ---- -- ------ ---------
			double previousAccountBalance = 0.0;
			for (Account account : accounts) {
				Assert
						.assertTrue(account.getBalance() >= previousAccountBalance);
				previousAccountBalance = account.getBalance();
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getSavingsAccounts
	 */
	@Test
	public void testGetSavingsAccounts() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<SavingsAccount> accounts = accountService.getSavingsAccounts();

			Assert.assertEquals(1, accounts.size());
			// Confirm data is sorted correctly
			// ------- ---- -- ------ ---------
			double previousAccountBalance = 0.0;
			for (Account account : accounts) {
				Assert
						.assertTrue(account.getBalance() >= previousAccountBalance);
				previousAccountBalance = account.getBalance();
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getAccounts -- min and max balance
	 */
	@Test
	public void testGetAccountsByMinMaxBalance() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Account> accounts = accountService.getAccounts(1000.00,
					4000.00);
			Assert.assertEquals(4, accounts.size());
			for (Account account : accounts) {
				Assert.assertTrue(account.getBalance() >= 1000.00
						&& account.getBalance() <= 4000.00);
			}
			System.out.println(accounts);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getAccounts -- accountIds
	 */
	@Test
	public void testGetAccountsByAccountIds() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Set<Long> accountIds = new HashSet<Long>();
			accountIds.add(new Long(1L));
			accountIds.add(new Long(2L));
			accountIds.add(new Long(3L));

			List<Account> accounts = accountService.getAccounts(accountIds);
			Assert.assertEquals(3, accounts.size());
			Set<Long> retrievedAccountIds = new HashSet<Long>();
			for (Account account : accounts) {
				retrievedAccountIds.add(account.getAccountId());
			}
			Assert.assertEquals(retrievedAccountIds.size(), accounts.size());
			Assert.assertTrue(accountIds.containsAll(retrievedAccountIds));

			System.out.println(accounts);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getAccounts -- transactionMaximum
	 */
	@Test
	public void testGetAccountsByTransactionMaximum() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Account> accounts = accountService.getAccounts(500L);
			Assert.assertEquals(3, accounts.size());
			for (Account account : accounts) {
				for (AccountTransaction accountTransaction : account
						.getAccountTransactions()) {
					Assert.assertTrue(accountTransaction.getAmount() <= 500L);
				}
			}
			System.out.println(accounts);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getAccountsAndTransactions
	 */
	@Test
	public void testGetAccountsAndTransactions() {
		Session session = null;
		AccountService accountService = new AccountService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Map<String, ?>> report = accountService
					.getAccountsAndTransactions();

			// Confirm that report size is equal to total number of transactions
			// in system
			// -----------------------------------------------------------------
			Assert.assertEquals(6, report.size());
			System.out.println(report);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getEBillerReport
	 */
	@Test
	public void testGetEBillerReport() {
		Session session = null;
		AccountService accountService = new AccountService();
		Map<Long, Map<Long, Double>> expectedAverageBalances = new HashMap<Long, Map<Long, Double>>();
		Map<Long, Map<Long, Long>> expectedCounts = new HashMap<Long, Map<Long, Long>>();

		HashMap<Long, Double> eBillerAverageMap = new HashMap<Long, Double>();
		HashMap<Long, Long> eBillerCountMap = new HashMap<Long, Long>();

		eBillerAverageMap.put(1L, 100.00);
		eBillerAverageMap.put(2L, 200.00);
		eBillerAverageMap.put(3L, 300.00);
		expectedAverageBalances.put(1L, eBillerAverageMap);
		eBillerCountMap.put(1L, 1L);
		eBillerCountMap.put(2L, 1L);
		eBillerCountMap.put(3L, 1L);
		expectedCounts.put(1L, eBillerCountMap);

		eBillerAverageMap = new HashMap<Long, Double>();
		eBillerAverageMap.put(1L, 400.00);
		eBillerAverageMap.put(3L, 600.00);
		expectedAverageBalances.put(2L, eBillerAverageMap);
		eBillerCountMap = new HashMap<Long, Long>();
		eBillerCountMap.put(1L, 1L);
		eBillerCountMap.put(3L, 3L);
		expectedCounts.put(2L, eBillerCountMap);

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<AccountEBillerSummary> accountEBillerReport = accountService
					.getAccountEBillerReport();

			Assert.assertEquals(5, accountEBillerReport.size());
			for (AccountEBillerSummary summary : accountEBillerReport) {
				Assert.assertEquals(expectedCounts.get(summary.getAccountId())
						.get(summary.getEbillerId()).longValue(), summary
						.getCount());
				Assert.assertEquals(expectedAverageBalances.get(
						summary.getAccountId()).get(summary.getEbillerId())
						.doubleValue(), summary.getAverageBalance());
			}

			System.out.println(accountEBillerReport);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/**
	 * Test getEBillerReportAsObjectArray
	 */
	@Test
	public void testGetEBillerReportAsObjectArray() {
		Session session = null;
		AccountService accountService = new AccountService();
		Map<Long, Map<Long, Double>> expectedAverageBalances = new HashMap<Long, Map<Long, Double>>();
		Map<Long, Map<Long, Long>> expectedCounts = new HashMap<Long, Map<Long, Long>>();

		HashMap<Long, Double> eBillerAverageMap = new HashMap<Long, Double>();
		HashMap<Long, Long> eBillerCountMap = new HashMap<Long, Long>();

		eBillerAverageMap.put(1L, 100.00);
		eBillerAverageMap.put(2L, 200.00);
		eBillerAverageMap.put(3L, 300.00);
		expectedAverageBalances.put(1L, eBillerAverageMap);
		eBillerCountMap.put(1L, 1L);
		eBillerCountMap.put(2L, 1L);
		eBillerCountMap.put(3L, 1L);
		expectedCounts.put(1L, eBillerCountMap);

		eBillerAverageMap = new HashMap<Long, Double>();
		eBillerAverageMap.put(1L, 400.00);
		eBillerAverageMap.put(3L, 600.00);
		expectedAverageBalances.put(2L, eBillerAverageMap);
		eBillerCountMap = new HashMap<Long, Long>();
		eBillerCountMap.put(1L, 1L);
		eBillerCountMap.put(3L, 3L);
		expectedCounts.put(2L, eBillerCountMap);

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Object[]> accountEBillerReport = accountService
					.getAccountEBillerReportAsObjectArray();

			Assert.assertEquals(5, accountEBillerReport.size());

			// accountEBillerReport is a List of Object arrays, where each array
			// has the following structure: summary[0] = accountId, summary[1] =
			// eBillerId, summary[2] = count, summary[3] = average
			// -----------------------------------------------------------------
			for (Object[] summary : accountEBillerReport) {
				Assert.assertEquals(expectedCounts.get(summary[0]).get(
						summary[1]), new Long((Integer) summary[2]));
				Assert.assertEquals(expectedAverageBalances.get(summary[0])
						.get(summary[1]), (Double) summary[3]);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			HibernateUtil.getSessionFactory().close();
		}
	}
}
