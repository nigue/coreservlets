package courses.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

		// Create EBillers and Accounts
        // ------ -------- --- --------
        EBiller ebiller1 = createEBiller();
        EBiller ebiller2 = createEBiller();

        Account account1 = buildCheckingAccount();
        Account account2 = buildCheckingAccount();
        AccountService accountService = new AccountService();
        accountService.saveOrUpdateAccount(account1);
        accountService.saveOrUpdateAccount(account2);

		// Register ebiller with accounts and set registeredBy to
        // new account owner
        // ------------------------------------------------------
        AccountOwner accountOwner = buildAccountOwner("111-11-1111");
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        accountOwnerService.saveOrUpdateAccountOwner(accountOwner);

        EBillerRegistration ebillerRegistration1 = accountService
                .registerEBiller(account1, ebiller1, accountOwner, "1111");
        EBillerRegistration ebillerRegistration2 = accountService
                .registerEBiller(account2, ebiller1, accountOwner, "1111");
        EBillerRegistration ebillerRegistration3 = accountService
                .registerEBiller(account2, ebiller2, accountOwner, "1111");

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

		// Load EBiller and ensure counts of accounts are correct
        // ---- ------- --- ------ ------ -- -------- --- -------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        EBillerService eBillerService = new EBillerService();
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
        HibernateUtil.getSessionFactory().getCurrentSession().close();

		// Unregister ebillers with accounts
        // ---------- -------- ---- --------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        accountService.unregisterEBiller(ebillerRegistration1);
        accountService.unregisterEBiller(ebillerRegistration2);
        accountService.unregisterEBiller(ebillerRegistration3);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

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
        HibernateUtil.getSessionFactory().getCurrentSession().close();

		// cleanup
        // -------
        deleteAccountOwner(accountOwner);
        deleteAccount(account1);
        deleteAccount(account2);
        deleteEBiller(ebiller1);
        deleteEBiller(ebiller2);
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test creation of multiple ebills for an ebiller. Ensure that all the
     * ebill collections (set, map, list, array, etc) in ebiller have the
     * correct number of ebills. Also, ensure that the list of balances loaded
     * by the collection mapping is correct.
     */
    @Test
    public void testCreateMultipleEBills() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        EBillService ebillService = new EBillService();
        List<EBill> ebills = new ArrayList<EBill>();

		// Create an ebiller
        // ------ -- -------
        EBiller ebiller = createEBiller();

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

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        long ebillerId = ebiller.getEbillerId();

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
        HibernateUtil.getSessionFactory().getCurrentSession().close();

		// cleanup
        // -------
        for (EBill ebill : ebills) {
            deleteAccount(ebill.getAccount());
        }
        deleteEBiller(ebiller);
        HibernateUtil.getSessionFactory().close();
    }
}
