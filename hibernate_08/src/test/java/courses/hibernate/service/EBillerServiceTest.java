package courses.hibernate.service;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Service layer tests for ebillers
 */
public class EBillerServiceTest extends ServiceTest {

    /**
     * Test getEbillsUsingOrdinaryJoins
     */
//    @Test
    public void testGetEbillsUsingOrdinaryJoins() {
        Session session = null;
        EBillerService ebillerService = new EBillerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<EBiller, List<EBill>> ebillerEBillMap = ebillerService
                    .getEbillsUsingOrdinaryJoins(500L);

            TestCase.assertEquals(3, ebillerEBillMap.size());
            for (EBiller ebiller : ebillerEBillMap.keySet()) {
                if (ebiller.getEbillerId() <= 2) {
                    TestCase.assertTrue(ebillerEBillMap.get(ebiller).size() == 1);
                }
                if (ebiller.getEbillerId() == 3) {
                    TestCase.assertTrue(ebillerEBillMap.get(ebiller).size() == 3);
                }
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
     * Test getEBillersUsingQBC
     */
//    @Test
    public void testGetEBillersUsingQBC() {
        Session session = null;
        EBillerService ebillerService = new EBillerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<EBiller> ebillers = ebillerService
                    .getEBillersUsingQBC(500.00);

            TestCase.assertEquals(1, ebillers.size());
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
     * Test getEBillersUsingHQL
     */
//    @Test
    public void testGetEBillersUsingHQL() {
        Session session = null;
        EBillerService ebillerService = new EBillerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<EBiller> ebillers = ebillerService
                    .getEBillersUsingHQL(500.00);

            TestCase.assertEquals(1, ebillers.size());
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
