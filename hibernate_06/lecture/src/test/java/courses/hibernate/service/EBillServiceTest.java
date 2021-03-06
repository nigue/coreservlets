package courses.hibernate.service;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
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
//    @Test
    public void testCreateEBill() {
        Session session = null;
        EBill ebill = null;
        EBiller ebiller = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

			// create the eBill
            // ------ --- -----
            ebiller = createEBiller();
            ebill = buildEBill(ebiller, createCheckingAccount());

			// save the eBill
            // ---- --- -----
            EBillService ebillService = new EBillService();
            ebillService.saveOrUpdateEBill(ebill);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        System.out.println("var ebill = " + ebill);

		// check that IDs were set after the hbm session
        // ----- ---- --- ---- --- ----- --- --- -------
        TestCase.assertNotNull(ebill.getEbillId());

		// cleanup
        // -------
        try {
            deleteAccount(ebill.getAccount());
            deleteEBiller(ebiller);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test payment of ebill by setting a new account transaction on ebill
     */
//    @Test
    public void testPayEBill() {
        Session session = null;
        EBill ebill = null;
        Account account = null;
        EBillService ebillService = new EBillService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            ebill = createEBill(account);
            AccountTransaction accountTransaction = createElectronicAccountTransaction(
                    account, ebill.getBalance());

			// Pay ebill by setting transaction on ebill
            // --- ----- -- ------- ----------- -- -----
            ebill.setAccountTransaction(accountTransaction);
            ebillService.saveOrUpdateEBill(ebill);

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

			// Validate that transaction has been persisted on ebill
            // -------- ---- ----------- --- ---- --------- -- -----
            ebill = ebillService.getEBill(ebill.getEbillId());
            TestCase.assertNotNull(ebill.getAccountTransaction());

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
        try {
            deleteAccount(account);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test payment of ebill by setting a new account transaction on a detatched
     * ebill
     */
//    @Test
    public void testPayBillWithMerge() {
        Session session = null;
        Account account = null;
        EBill ebill = null;
        AccountTransaction accountTransaction = null;
        EBillService ebillService = new EBillService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            ebill = createEBill(account);
            accountTransaction = createElectronicAccountTransaction(account,
                    ebill.getBalance());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        ebill.setAccountTransaction(accountTransaction);

        try {
			// Pay ebill by setting transaction on detached ebill and merging it
            // --- ----- -- ------- ----------- -- -------- ----- --- ------- --
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebillService.mergeEBill(ebill);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        try {
			// Validate that transaction has been persisted on ebill
            // -------- ---- ----------- --- ---- --------- -- -----
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebill = ebillService.getEBill(ebill.getEbillId());
            TestCase.assertNotNull(ebill.getAccountTransaction());

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
        try {
            deleteAccount(account);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test pay ebill with lock
     */
//    @Test
    public void testPayEbillWithLock() {
        Session session = null;
        Account account = null;
        EBill ebill = null;
        AccountTransaction accountTransaction = null;
        EBillService ebillService = new EBillService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            ebill = createEBill(account);
            accountTransaction = createElectronicAccountTransaction(account,
                    ebill.getBalance());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

		// Set transaction on ebill prior to locking it
        // --- ----------- -- ----- ----- -- ------- --
        ebill.setAccountTransaction(accountTransaction);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebillService.reattachEBillWithLock(ebill);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Validate that transaction has not been persisted on ebill because
            // updates to object before lock should not be persisted
            //------------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebill = ebillService.getEBill(ebill.getEbillId());
            TestCase.assertNull(ebill.getAccountTransaction());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        try {
			// Lock ebill and then set transaction on it
            // ---- ----- --- ---- --- ----------- -- --
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebillService = new EBillService();
            ebillService.reattachEBillWithLock(ebill);
            ebill.setAccountTransaction(accountTransaction);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
		// Validate that transaction has been persisted on ebill
        // -------- ---- ----------- --- ---- --------- -- -----
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebill = ebillService.getEBill(ebill.getEbillId());
            TestCase.assertNotNull(ebill.getAccountTransaction());

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
        try {
            deleteAccount(account);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test payment of ebill by setting a new account transaction and have the
     * account transaction be created as part of the cascade.
     */
    @Test
    public void testPayBillWithCascade() {

        Session session = null;
        EBill ebill = null;
        Account account = null;
        EBillService ebillService = new EBillService();

        try {
			// Create EBill and build an Account Transaction and set it on
            // eBill. Don't explicitly create Account Transaction -- it is
            // automatically created when EBill is updated
            // ------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            ebill = createEBill(account);
            AccountTransaction accountTransaction = buildElectronicAccountTransaction(
                    account, ebill.getBalance());

            ebill.setAccountTransaction(accountTransaction);
            ebillService.saveOrUpdateEBill(ebill);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Validate that transaction has been persisted on ebill
            // -------- ---- ----------- --- ---- --------- -- -----
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebill = ebillService.getEBill(ebill.getEbillId());
            TestCase.assertNotNull(ebill.getAccountTransaction());

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
        try {
            deleteAccount(account);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
