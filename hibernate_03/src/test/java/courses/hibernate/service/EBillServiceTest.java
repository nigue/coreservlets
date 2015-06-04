package courses.hibernate.service;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
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
		EBiller ebiller = createEBiller();
		EBill ebill = buildEBill(ebiller);
		TestCase.assertTrue(ebill.getEbillId() == 0);

		// save the eBill
		// ---- --- -----
		EBillService ebillService = new EBillService();
		ebillService.saveOrUpdateEBill(ebill);

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();

		System.out.println("var ebill = " + ebill);

		// check that IDs were set after the hbm session
		// ----- ---- --- ---- --- ----- --- --- -------
		TestCase.assertTrue(ebill.getEbillId() > 0);

		// cleanup
		// -------
		deleteEBill(ebill);
		deleteEBiller(ebiller);
		HibernateUtil.getSessionFactory().close();
	}

	/**
	 * Test payment of ebill by setting a new account transaction on ebill
	 */
//	@Test
	public void testPayEBill() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		EBill ebill = createEBill();
		AccountTransaction accountTransaction = createAccountTransaction(ebill
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
		HibernateUtil.getSessionFactory().close();
	}
}
