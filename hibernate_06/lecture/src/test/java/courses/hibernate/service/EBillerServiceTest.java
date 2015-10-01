package courses.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;
import courses.hibernate.vo.EBillerRegistration;

/**
 * Service layer tests for ebillers
 */
public class EBillerServiceTest extends ServiceTest {

    /**
     * Test many to many relationship of account-ebillers. Create two ebillers
     * and accounts and build associations. Ensure that retrieval of the
     * ebillers and accounts have the appropriate relationships.
     */
//    @Test
    public void testManyToManyAccountEBillers() {
        Session session = null;
        AccountService accountService = new AccountService();
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        EBillerService eBillerService = new EBillerService();
        EBiller ebiller1 = null;
        EBiller ebiller2 = null;
        Account account1 = null;
        Account account2 = null;
        EBillerRegistration ebillerRegistration1 = null;
        EBillerRegistration ebillerRegistration2 = null;
        EBillerRegistration ebillerRegistration3 = null;
        AccountOwner accountOwner = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

			// Create EBillers and Accounts
            // ------ -------- --- --------
            ebiller1 = createEBiller();
            ebiller2 = createEBiller();

            account1 = buildCheckingAccount();
            account2 = buildCheckingAccount();
            accountService.saveOrUpdateAccount(account1);
            accountService.saveOrUpdateAccount(account2);

			// Register ebiller with accounts and set registeredBy to
            // new account owner
            // ------------------------------------------------------
            accountOwner = buildAccountOwner("111-11-1111");
            accountOwnerService.saveOrUpdateAccountOwner(accountOwner);

            ebillerRegistration1 = accountService.registerEBiller(account1,
                    ebiller1, accountOwner, "1111");
            ebillerRegistration2 = accountService.registerEBiller(account2,
                    ebiller1, accountOwner, "1111");
            ebillerRegistration3 = accountService.registerEBiller(account2,
                    ebiller2, accountOwner, "1111");

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Load EBiller and ensure counts of accounts are correct
            // ---- ------- --- ------ ------ -- -------- --- -------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebiller1 = eBillerService.getEBiller(ebiller1.getEbillerId());
            ebiller2 = eBillerService.getEBiller(ebiller2.getEbillerId());
            TestCase.assertEquals(2, ebiller1.getEbillerRegistrations().size());
            TestCase.assertEquals(1, ebiller2.getEbillerRegistrations().size());

			// Load Accounts and ensure counts of ebillers are correct
            // ---- -------- --- ------ ------ -- -------- --- -------
            account1 = accountService.getAccount(account1.getAccountId());
            account2 = accountService.getAccount(account2.getAccountId());
            TestCase.assertEquals(1, account1.getEbillerRegistrations().size());
            TestCase.assertEquals(2, account2.getEbillerRegistrations().size());

            System.out.println(ebiller1);
            System.out.println(ebiller2);
            System.out.println(account1);
            System.out.println(account2);
            System.out.println(ebillerRegistration1);
            System.out.println(ebillerRegistration2);
            System.out.println(ebillerRegistration3);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Unregister ebillers with accounts
            // ---------- -------- ---- --------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountService.unregisterEBiller(ebillerRegistration1);
            accountService.unregisterEBiller(ebillerRegistration2);
            accountService.unregisterEBiller(ebillerRegistration3);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Load EBiller and ensure counts of accounts are correct
            // ---- ------- --- ------ ------ -- -------- --- -------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            ebiller1 = eBillerService.getEBiller(ebiller1.getEbillerId());
            ebiller2 = eBillerService.getEBiller(ebiller2.getEbillerId());
            TestCase.assertEquals(0, ebiller1.getEbillerRegistrations().size());
            TestCase.assertEquals(0, ebiller2.getEbillerRegistrations().size());

			// Load Accounts and ensure counts of ebillers are correct
            // ---- -------- --- ------ ------ -- -------- --- -------
            account1 = accountService.getAccount(account1.getAccountId());
            account2 = accountService.getAccount(account2.getAccountId());
            TestCase.assertEquals(0, account1.getEbillerRegistrations().size());
            TestCase.assertEquals(0, account2.getEbillerRegistrations().size());

            System.out.println(ebiller1);
            System.out.println(ebiller2);
            System.out.println(account1);
            System.out.println(account2);
            System.out.println(ebillerRegistration1);
            System.out.println(ebillerRegistration2);
            System.out.println(ebillerRegistration3);

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
            deleteAccountOwner(accountOwner);
            deleteAccount(account1);
            deleteAccount(account2);
            deleteEBiller(ebiller1);
            deleteEBiller(ebiller2);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test creation of multiple ebills for an ebiller. Ensure that all the
     * ebill collections (set, map, list, array, etc) in ebiller have the
     * correct number of ebills. Also, ensure that the list of balances loaded
     * by the collection mapping is correct.
     */
    @Test
    public void testCreateMultipleEBills() {

        Session session = null;
        EBillService ebillService = new EBillService();
        List<EBill> ebills = new ArrayList<EBill>();
        EBiller ebiller = null;
        long ebillerId = 0;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

			// Create an ebiller
            // ------ -- -------
            ebiller = createEBiller();

			// Create 10 ebills for ebiller. Because this is a set, need
            // some time between the ebills to set a different due date,
            // hence the sleep
            // ---------------------------------------------------------
            for (int i = 0; i < 10; i++) {
                EBill ebill = buildEBill(ebiller, createCheckingAccount());
                ebills.add(ebill);
                ebillService.saveOrUpdateEBill(ebill);
                sleep(100);
            }
            ebillerId = ebiller.getEbillerId();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Retrieve the persisted ebiller and ensure that the
            // ebiller contains the 10 ebills
            // ---------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            EBillerService eBillerService = new EBillerService();

            ebiller = eBillerService.getEBiller(ebillerId);
            System.out.println(ebiller);
            TestCase.assertEquals(10, ebiller.getEbills().size());

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
            for (EBill ebill : ebills) {
                deleteAccount(ebill.getAccount());
            }
            deleteEBiller(ebiller);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
