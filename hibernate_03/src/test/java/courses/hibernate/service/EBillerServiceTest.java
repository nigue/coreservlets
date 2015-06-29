package courses.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Service layer tests for ebillers
 */
public class EBillerServiceTest extends ServiceTest {

    /**
     * Test many to many relationship of account-ebillers. Create two ebillers
     * and accounts and build associations. Ensure that retrieval of the
     * ebillers and accounts have the appropriate relationships.
     */
    @Test
    public void testManyToManyAccountEBillers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //Create EBillers and Accounts
        //------ -------- --- --------
        EBiller ebiller1 = createEBiller();
        EBiller ebiller2 = createEBiller();

        Account account1 = buildAccount();
        account1.addEbiller(ebiller1);
        Account account2 = buildAccount();
        account2.addEbiller(ebiller1);
        account2.addEbiller(ebiller2);

        AccountService accountService = new AccountService();
        accountService.saveOrUpdateAccount(account1);
        accountService.saveOrUpdateAccount(account2);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        //Load EBiller and ensure counts of accounts are correct
        //---- ------- --- ------ ------ -- -------- --- -------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        EBillerService eBillerService = new EBillerService();
        ebiller1 = eBillerService.getEBiller(ebiller1.getEbillerId());
        ebiller2 = eBillerService.getEBiller(ebiller2.getEbillerId());
        TestCase.assertEquals(2, ebiller1.getAccounts().size());
        TestCase.assertEquals(1, ebiller2.getAccounts().size());

        //Load Accounts and ensure counts of ebillers are correct
        //---- -------- --- ------ ------ -- -------- --- -------
        account1 = accountService.getAccount(account1.getAccountId());
        account2 = accountService.getAccount(account2.getAccountId());
        TestCase.assertEquals(1, account1.getEbillers().size());
        TestCase.assertEquals(2, account2.getEbillers().size());

        System.out.println(ebiller1);
        System.out.println(ebiller2);
        System.out.println(account1);
        System.out.println(account2);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        deleteAccount(account2);
        deleteAccount(account1);
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
//	@Test
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
            EBill ebill = buildEBill(ebiller);
            ebill.setEbillerEbillNumber(i);
            ebills.add(ebill);
            ebillService.saveOrUpdateEBill(ebill);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        long ebillerId = ebiller.getEbillerId();

        EBillerService eBillerService = new EBillerService();

        // Retrieve the persisted ebillers and ensure that all
        // the mapped collections hold the 10 ebills that were
        // persisted above.
        // ---------------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        ebiller = eBillerService.getEBiller(ebillerId);
        System.out.println(ebiller);
        TestCase.assertEquals(10, ebiller.getEbillsArray().length);
        TestCase.assertEquals(10, ebiller.getEbillsBag().size());
        TestCase.assertEquals(10, ebiller.getEbillsList().size());
        TestCase.assertEquals(10, ebiller.getEbillsMap().size());
        TestCase.assertEquals(10, ebiller.getEbillsSet().size());
        TestCase.assertEquals(10, ebiller.getEbillsSortedMap().size());
        TestCase.assertEquals(10, ebiller.getEbillsSortedSet().size());
        TestCase.assertEquals(10, ebiller.getEbillBalances().size());

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        for (EBill ebill : ebills) {
            deleteEBill(ebill);
        }
        deleteEBiller(ebiller);
        HibernateUtil.getSessionFactory().close();
    }
}
