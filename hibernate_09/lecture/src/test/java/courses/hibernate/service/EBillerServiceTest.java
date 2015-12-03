package courses.hibernate.service;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBiller;

/**
 * Service layer tests for ebillers
 */
public class EBillerServiceTest extends ServiceTest {

	@AfterClass
	public static void closeHibernateSessionFactory() {
		HibernateUtil.getSessionFactory().close();
	}

	/**
	 * Test getEBillersUsingHQL
	 */
	//@Test
	public void testGetEBillersUsingHQL() {
		Session session = null;
		EBillerService ebillerService = new EBillerService();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<EBiller> ebillers = ebillerService.getEBillersUsingHQL(500.00);

			TestCase.assertEquals(1, ebillers.size());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			TestCase.fail();
		} finally {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		}
	}

}
