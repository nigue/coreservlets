package courses.hibernate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.SavingsAccount;
import org.hibernate.HibernateException;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest extends ServiceTest {

    /**
     * Test account creation
     */
//    @Test
    public void testCreateAccount() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // create the account
        // ------ --- -------
        SavingsAccount account = new SavingsAccount();

        // no need to set id, Hibernate does it for us
        // -- ---- -- --- --- --------- ---- -- --- --
        // account.setAccountId(1)
        account.setCreationDate(new Date());
        account.setBalance(1000L);
        account.setInterestRate(3.5);

        TestCase.assertTrue(account.getAccountId() == 0);

        // save the account
        // ---- --- -------
        AccountService accountService = new AccountService();
        accountService.saveOrUpdateAccount(account);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        System.out.println("var account = " + account);

        // check that IDs were set after the hbm session
        // ----- ---- --- ---- --- ----- --- --- -------
        TestCase.assertTrue(account.getAccountId() > 0);

        // cleanup
        // -------
        deleteAccount(account);
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test retrieval of account
     */
//    @Test
    public void testGetAccount() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Account account = createSavingsAccount();

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        System.out.println("var account = " + account);
        AccountService accountService = new AccountService();
        Account anotherCopy = accountService.getAccount(account.getAccountId());

        System.out.println("var anotherCopy = " + anotherCopy);

        // make sure these are two separate instances
        // ---- ---- ----- --- --- -------- ---------
        TestCase.assertTrue(account != anotherCopy);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        deleteAccount(account);
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test updating of account balance
     */
//    @Test
    public void testUpdateAccountBalance() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Account account = createCheckingAccount();
        System.out.println("var account = " + account);

        AccountService accountService = new AccountService();
        account.setBalance(2000);
        accountService.saveOrUpdateAccount(account);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        Session session2 = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session2.beginTransaction();

        Account anotherCopy = accountService.getAccount(account.getAccountId());
        System.out.println("var anotherCopy = " + anotherCopy);

        // make sure the one we just pulled back
        // from the database has the updated balance
        // -----------------------------------------
        TestCase.assertTrue(anotherCopy.getBalance() == 2000);

        session2.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        deleteAccount(account);
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test deletion of account
     */
//    @Test
    public void testDeleteAccount() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Account account = createCheckingAccount();
        System.out.println("var account = " + account);

        // delete the account
        // ------ --- -------
        AccountService accountService = new AccountService();
        accountService.deleteAccount(account);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // try to get the account again -- should be null
        // --- -- --- --- ------- ----- -- ------ -- ----
        Session session2 = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session2.beginTransaction();
        Account anotherCopy = accountService.getAccount(account.getAccountId());

        System.out.println("var anotherCopy = " + anotherCopy);

        TestCase.assertNull(anotherCopy);

        session2.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test many to many relationship of account-accountOwners. Create two
     * accounts and accountOwners and build associations. Ensure that retrieval
     * of the accountOwners and accounts have the appropriate relationships.
     */
//    @Test
    public void testManyToManyAccountAccountOwners() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Create AccountOwners and Accounts
        // ------ ------------- --- --------
        AccountOwner accountOwner1 = createAccountOwner("111-11-1111");
        AccountOwner accountOwner2 = createAccountOwner("222-22-2222");

        Account account1 = buildSavingsAccount();
        account1.addAccountOwner(accountOwner1);
        Account account2 = buildSavingsAccount();
        account2.addAccountOwner(accountOwner1);
        account2.addAccountOwner(accountOwner2);

        AccountService accountService = new AccountService();
        accountService.saveOrUpdateAccount(account1);
        accountService.saveOrUpdateAccount(account2);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // Load AccountOwner and ensure counts of accounts are correct
        // ---- ------------ --- ------ ------ -- -------- --- -------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        AccountOwnerService accountOwnerService = new AccountOwnerService();

        accountOwner1 = accountOwnerService.getAccountOwner(accountOwner1.getAccountOwnerId());
        accountOwner2 = accountOwnerService.getAccountOwner(accountOwner2.getAccountOwnerId());

        TestCase.assertEquals(2, accountOwner1.getAccounts().size());
        TestCase.assertEquals(1, accountOwner2.getAccounts().size());

		// Load Accounts and ensure counts of AccountOwners are correct
        // ---- -------- --- ------ ------ -- ------------- --- -------
        try {
            Account accountNew2 = accountService.getAccount(account2.getAccountId());
            Account accountNew1 = accountService.getAccount(account1.getAccountId());
            TestCase.assertEquals(1, accountNew1.getAccountOwners().size());
            TestCase.assertEquals(2, accountNew2.getAccountOwners().size());
            TestCase.assertEquals(1, accountNew1.getAccountOwnerAddresses().size(), 2);
            TestCase.assertEquals(2, accountNew2.getAccountOwnerAddresses().size(), 2);

            System.out.println(accountOwner1);
            System.out.println(accountOwner2);
            System.out.println(account1);
            System.out.println(account2);

            session.getTransaction().commit();
            HibernateUtil.getSessionFactory().getCurrentSession().close();

		// cleanup
            // -------
            deleteAccount(account1);
            deleteAccount(account2);
//            deleteAccountOwner(accountOwner1);
//            deleteAccountOwner(accountOwner2);
            HibernateUtil.getSessionFactory().close();

        } catch (HibernateException e) {
            System.out.println(e.getLocalizedMessage());

            deleteAccountOwner(accountOwner1);
            deleteAccountOwner(accountOwner2);
        }
    }

    /**
     * Test creation of multiple ebills for an account.
     */
//    @Test
    public void testCreateMultipleEBills() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        EBillService ebillService = new EBillService();
        List<EBill> ebills = new ArrayList<EBill>();

        // Create an account
        // ------ -- -------
        Account account = createCheckingAccount();

        // Create 10 ebills for account. Because this is a set, need
        // some time between the ebills to set a different due date,
        // hence the sleep
        // ---------------------------------------------------------
        for (int i = 0; i < 10; i++) {
            EBill ebill = buildEBill(createEBiller(), account);
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

        long accountId = account.getAccountId();

        // Retrieve the persisted account and ensure that the account
        // contains the 10 ebills.
        // ----------------------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        AccountService accountService = new AccountService();

        account = accountService.getAccount(accountId);
        System.out.println(account);
        TestCase.assertEquals(10, account.getEbills().size());

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        for (EBill ebill : ebills) {
            deleteEBill(ebill);
        }
        deleteAccount(account);
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test creation of multiple transactions for an account.
     */
//    @Test
    public void testCreateMultipleAccountTransactions() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        AccountTransactionService accountTransactionService = new AccountTransactionService();
        List<AccountTransaction> accountTransactions = new ArrayList<AccountTransaction>();

        // Create an account
        // ------ -- -------
        Account account = createCheckingAccount();

        // Create 10 account transactions for account. Because this is a
        // set, need some time between the ebills to set a different due
        // date, hence the sleep
        // ---------------------------------------------------------
        for (int i = 0; i < 10; i++) {
            AccountTransaction accountTransaction = createAccountTransaction(
                    account, 10);
            accountTransactions.add(accountTransaction);
            accountTransactionService
                    .saveOrUpdateAccountTransaction(accountTransaction);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        long accountId = account.getAccountId();

        // Retrieve the persisted account and ensure that the account
        // contains the 10 transactions.
        // ----------------------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        AccountService accountService = new AccountService();

        account = accountService.getAccount(accountId);
        System.out.println(account);
        TestCase.assertEquals(10, account.getAccountTransactions().size());
        TestCase.assertEquals(new Double(900), account.getBalance());

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        for (AccountTransaction accountTransaction : accountTransactions) {
            deleteAccountTransaction(accountTransaction);
        }
        deleteAccount(account);
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test Account Inheritance
     */
//    @Test
    public void testAccountInheritance() {

        // Create two checking accounts and three savings accounts
        // ------ --- -------- -------- --- ----- ------- --------
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        createCheckingAccount();
        createCheckingAccount();
        createSavingsAccount();
        createSavingsAccount();
        createSavingsAccount();

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // Retrieve the accounts and confirm that 5 accounts are retrieved,
        // 2 checking accounts, and 3 savings accounts
        // ----------------------------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        AccountService accountService = new AccountService();
        List<Account> accounts = accountService.getAccounts();
        List<CheckingAccount> checkingAccounts = accountService
                .getCheckingAccounts();
        List<SavingsAccount> savingsAccounts = accountService
                .getSavingsAccounts();

        TestCase.assertEquals(5, accounts.size());
        TestCase.assertEquals(2, checkingAccounts.size());
        TestCase.assertEquals(3, savingsAccounts.size());

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        for (Account account : accounts) {
            deleteAccount(account);
        }
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test updating of account balance with different flush modes
     */
    @Test
    public void testUpdateAccountBalanceWithFlushModes() {

        // Flush mode by default is AUTO, so update is changed when
        // transaction commits without an explicit call to save.
        // --------------------------------------------------------
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Account account = createCheckingAccount();
        System.out.println("var account = " + account);

        AccountService accountService = new AccountService();
        account.setBalance(2000);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // make sure the one we just pulled back from the database has
        // the updated balance
        // -----------------------------------------------------------
        Session session2 = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session2.beginTransaction();

        account = accountService.getAccount(account.getAccountId());
        System.out.println(account);

        TestCase.assertTrue(account.getBalance() == 2000);

        session2.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // Change flush mode to manual and confirm that update does not
        // occur when transaction commits.
        // ------------------------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.setFlushMode(FlushMode.MANUAL);
        session.beginTransaction();
        account.setBalance(5000);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // make sure the one we just pulled back from the database does not
        // have the updated balance
        // ----------------------------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        account = accountService.getAccount(account.getAccountId());
        System.out.println(account);
        TestCase.assertTrue(account.getBalance() == 2000);

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().getCurrentSession().close();

        // cleanup
        // -------
        deleteAccount(account);
        HibernateUtil.getSessionFactory().close();
    }
}
