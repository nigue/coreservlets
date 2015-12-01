package courses.hibernate.service;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBill;

/**
 * Service layer tests for ebills
 */
public class EBillServiceTest extends ServiceTest {

	/**
	 * Test getEbills
	 */
	@Test
	public void testGetBills() {
		Session session = null;
		EBillService ebillService = new EBillService();
		List<EBill> ebills = null;
		try {
			// Get Ebills without a JOIN and this should not set accounts with a
			// proxy object by default
			// -----------------------------------------------------------------
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			ebills = ebillService.getEBills("VISA");

			Assert.assertEquals(2, ebills.size());
			for (EBill ebill : ebills) {
				Assert.assertEquals(ebill.getEbiller().getName(), "VISA");
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		// Confirm that account is a proxy object -- retrieving balance will
		// result in an Exception since ebills are detached
		// -----------------------------------------------------------------
		boolean reachedException = false;
		try {
			for (EBill ebill : ebills) {
				ebill.getAccount().getBalance();
			}
		} catch (HibernateException e) {
			reachedException = true;
		}
		Assert.assertTrue(reachedException);

		// Retrieve Ebills, but now use a JOIN to load account
		// -------- ------- --- --- --- - ---- -- ---- -------
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			ebills = ebillService.getEBillsAndLoadAccount("VISA");

			Assert.assertEquals(2, ebills.size());
			for (EBill ebill : ebills) {
				Assert.assertEquals(ebill.getEbiller().getName(), "VISA");
			}

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}

		// Should be able to retrieve account attributes from the detatched
		// ebills
		// ----------------------------------------------------------------
		try {
			for (EBill ebill : ebills) {
				ebill.getAccount().getBalance();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			HibernateUtil.getSessionFactory().close();
		}

	}

}
