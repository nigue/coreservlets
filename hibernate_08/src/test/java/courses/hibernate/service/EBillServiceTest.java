package courses.hibernate.service;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
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
     * Test getEBillsAndEBillersUsingImplicitAssociationJoin
     */
//    @Test
    public void testGetEBillsAndEBillersUsingImplicitAssociationJoin() {
        Session session = null;
        EBillService ebillService = new EBillService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<EBill> ebills = ebillService
                    .getEBillsAndEBillersUsingImplicitAssociationJoin("VISA");

            TestCase.assertEquals(3, ebills.size());
            for (EBill ebill : ebills) {
                TestCase.assertTrue(ebill.getEbiller().getName().equals("VISA"));
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
     * Test getEBillsAndEBillersUsingOrdinaryJoin
     */
//    @Test
    public void testGetEBillsAndEBillersUsingOrdinaryJoin() {
        Session session = null;
        EBillService ebillService = new EBillService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<EBill, EBiller> ebillEbillerMap = ebillService
                    .getEBillsAndEBillersUsingOrdinaryJoin("VISA");

            TestCase.assertEquals(3, ebillEbillerMap.size());
            for (EBill ebill : ebillEbillerMap.keySet()) {
                TestCase.assertTrue(ebill.getEbiller().getName().equals("VISA"));
                TestCase.assertTrue(ebillEbillerMap.get(ebill).getName().equals("VISA"));
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
     * Test getEBillsAndAccountTransactionsUsingLeftOuterJoin
     */
//    @Test
    public void testGetEBillsAndAccountTransactionsUsingLeftOuterJoin() {
        Session session = null;
        EBillService ebillService = new EBillService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<EBill, AccountTransaction> ebillsAccountTransactionMap = ebillService
                    .getEBillsAndAccountTransactionsUsingLeftOuterJoin(500);

            TestCase.assertEquals(5, ebillsAccountTransactionMap.size());
            int accountTransactionCount = 0;
            for (EBill ebill : ebillsAccountTransactionMap.keySet()) {
                AccountTransaction accountTransaction = ebillsAccountTransactionMap
                        .get(ebill);
                if (accountTransaction != null) {
                    accountTransactionCount++;
                }
            }
            TestCase.assertEquals(2, accountTransactionCount);

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
     * Test getEBillsAndAccountTransactionsUsingFetchJoin
     */
//    @Test
    public void testGetEBillsAndAccountTransactionsUsingFetchJoin() {
        Session session = null;
        EBillService ebillService = new EBillService();
        List<EBill> ebills = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            ebills = ebillService
                    .getEBillsAndAccountTransactionsUsingFetchJoin(500);

            TestCase.assertEquals(5, ebills.size());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }

        // Confirm that accountTransactions have been loaded -- no proxies
        // ------- ---- ------------------- ---- ---- ------ -- -- -------
        int accountTransactionCount = 0;
        for (EBill ebill : ebills) {
            AccountTransaction accountTransaction = ebill
                    .getAccountTransaction();
            if (accountTransaction != null) {
                accountTransactionCount++;
            }
        }
        TestCase.assertEquals(2, accountTransactionCount);
    }

    /**
     * Test getEBillsUsingNativeSQLandEntities
     */
//    @Test
    public void testGetEBillsUsingNativeSQLandEntities() {
        Session session = null;
        EBillService ebillService = new EBillService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<EBill> ebills = ebillService
                    .getEBillsUsingNativeSQLandEntities();

            TestCase.assertEquals(9, ebills.size());
            for (EBill ebill : ebills) {
                TestCase.assertNotNull(ebill);
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
     * Test getEBillBalances
     */
//    @Test
    public void testGetEBillBalances() {
        Session session = null;
        EBillService ebillService = new EBillService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<Long, Double> ebillBalances = ebillService
                    .getEBillBalances();

            TestCase.assertEquals(9, ebillBalances.size());
            for (Long ebillId : ebillBalances.keySet()) {
                TestCase.assertTrue(ebillId > 0);
                TestCase.assertTrue(ebillBalances.get(ebillId) > 0);
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
     * Test getEBillEBillers
     */
//    @Test
    public void testGetEBillEBillers() {
        Session session = null;
        EBillService ebillService = new EBillService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Map<Long, EBiller> ebillEbillers = ebillService
                    .getEBillEBillers();

            TestCase.assertEquals(9, ebillEbillers.size());
            for (Long ebillId : ebillEbillers.keySet()) {
                TestCase.assertTrue(ebillId > 0);
                TestCase.assertTrue(ebillEbillers.get(ebillId) != null);
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
