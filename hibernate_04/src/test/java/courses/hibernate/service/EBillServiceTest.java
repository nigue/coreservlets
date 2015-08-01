package courses.hibernate.service;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Service layer tests for ebills
 */
public class EBillServiceTest extends ServiceTest {

	/**
	 * Test creating of ebill
	 */
//	@Test
	public void testCreateEBill() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		// create the eBill
		// ------ --- -----
		Account account = createCheckingAccount();
		EBiller ebiller = createEBiller();
		EBill ebill = buildEBill(ebiller, account);

		// save the eBill
		// ---- --- -----
		EBillService ebillService = new EBillService();
		ebillService.saveOrUpdateEBill(ebill);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();

		System.out.println("var ebill = " + ebill);

		// check that IDs were set after the hbm session
		// ----- ---- --- ---- --- ----- --- --- -------
		TestCase.assertNotNull(ebill.getEbillId());

		// cleanup
		// -------
		deleteEBill(ebill);
		deleteEBiller(ebiller);
		deleteAccount(account);
		HibernateUtil.getSessionFactory().close();		
	}

	/**
	 * Test payment of ebill by setting a new account transaction on ebill
	 */
//	@Test
	public void testPayEBill() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Account account = createCheckingAccount();
		EBill ebill = createEBill(account);
		AccountTransaction accountTransaction = createAccountTransaction(account,ebill
				.getBalance());

		// Pay ebill by setting transaction on ebill
		// --- ----- -- ------- ----------- -- -----
		ebill.setAccountTransaction(accountTransaction);
		EBillService ebillService = new EBillService();
		ebillService.saveOrUpdateEBill(ebill);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();

		// Validate that transaction has been persisted on ebill
		// -------- ---- ----------- --- ---- --------- -- -----
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		ebill = ebillService.getEBill(ebill.getEbillId());
		TestCase.assertNotNull(ebill.getAccountTransaction());

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();

		// cleanup
		// -------
		deleteEBill(ebill);
		deleteAccountTransaction(accountTransaction);
		deleteAccount(account);
		HibernateUtil.getSessionFactory().close();		
	}
}
