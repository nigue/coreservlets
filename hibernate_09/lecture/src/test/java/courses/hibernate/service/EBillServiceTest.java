package courses.hibernate.service;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Service layer tests for ebills
 */
public class EBillServiceTest extends ServiceTest {
	
	@AfterClass
	public static void closeHibernateSessionFactory() {
		HibernateUtil.getSessionFactory().close();
	}


	/**
	 * Test creating of ebill using stored procedures
	 */
	//@Test
	public void testCreateEBill() {
		Session session = null;
		Account account = null;
		EBiller ebiller = null;
		EBill ebill = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			account = createCheckingAccount();
			ebiller = createEBiller();
			ebill = buildEBill(ebiller, account);

			EBillService ebillService = new EBillService();
			ebillService.saveOrUpdateEBill(ebill);

			session.getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();

			System.out.println("var ebill = " + ebill);

			// Confirm that creation date was set
			// ------- ---- -------- ---- --- ---
			TestCase.assertNotNull(ebill.getCreationDate());
		} catch (HibernateException e) {

			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			deleteAccount(account);
			deleteEBiller(ebiller);

			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test update of ebill and retrieval of updateDate after it's set by
	 * trigger
	 */
	//@Test
	public void testUpdateEBill() {
		Session session = null;
		Account account = null;
		EBiller ebiller = null;
		EBill ebill = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			account = createCheckingAccount();
			ebiller = createEBiller();
			ebill = buildEBill(ebiller, account);

			EBillService ebillService = new EBillService();
			ebillService.saveOrUpdateEBill(ebill);
			
			//Update date should be null
			//------ ---- ------ -- ----
			TestCase.assertNull(ebill.getUpdateDate());
			
			//Update ebill by setting transaction on it
			//------ ----- -- ------- ----------- -- --
			ebill.setAccountTransaction(createElectronicAccountTransaction(account, 10L));

			session.getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();

			System.out.println("var ebill = " + ebill);

			// Confirm that update date was set
			// ------- ---- ------ ---- --- ---
			TestCase.assertNotNull(ebill.getUpdateDate());
		} catch (HibernateException e) {

			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			deleteAccount(account);
			deleteEBiller(ebiller);

			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

	/**
	 * Test getEbills with functions
	 */
	//@Test
	public void testGetEBills() {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			EBillService ebillService = new EBillService();
			List<EBill> ebills = ebillService.getEBills();

			TestCase.assertEquals(9, ebills.size());
			HibernateUtil.getSessionFactory().close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}
}
