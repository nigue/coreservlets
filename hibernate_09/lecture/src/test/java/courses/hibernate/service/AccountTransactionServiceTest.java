package courses.hibernate.service;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountTransaction;

public class AccountTransactionServiceTest extends ServiceTest {

	@AfterClass
	public static void closeHibernateSessionFactory() {
		HibernateUtil.getSessionFactory().close();
	}

	/**
	 * Test create account transaction -- using event listeners
	 */
	//@Test
	public void testCreateAccountTransaction() {
		Session session = null;
		Account account = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			AccountTransactionService accountTransactionService = new AccountTransactionService();

			account = createCheckingAccount();
			AccountTransaction accountTransaction = createElectronicAccountTransaction(
					account, 10);

			accountTransactionService
					.saveOrUpdateAccountTransaction(accountTransaction);

			// Confirm transaction has a transaction date
			// ------- ----------- --- - ----------- ----
			TestCase.assertNotNull(accountTransaction.getTransactionDate());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			deleteAccount(account);
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

}
