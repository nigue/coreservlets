package courses.hibernate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import junit.framework.TestCase;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.CdAccount;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.ElectronicAccountTransaction;
import courses.hibernate.vo.PhysicalAccountTransaction;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest extends ServiceTest {

    /**
     * Test account creation
     */
//    @Test
    public void testCreateAccount() {

        SavingsAccount account = new SavingsAccount();
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account.setCreationDate(new Date());
            account.setBalance(1000L);
            account.setInterestRate(3.5);

            TestCase.assertTrue(account.getAccountId() == 0);

			// save the account
            // ---- --- -------
            AccountService accountService = new AccountService();
            accountService.saveOrUpdateAccount(account);

            session.getTransaction().commit();
            System.out.println("var account = " + account);

			// check that IDs were set after the hbm session
            // ----- ---- --- ---- --- ----- --- --- -------
            TestCase.assertTrue(account.getAccountId() > 0);
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
     * Test retrieval of account
     */
//    @Test
    public void testGetAccount() {
        Account account = null;
        Session session = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            account = createSavingsAccount();
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

            System.out.println("var account = " + account);
            AccountService accountService = new AccountService();
            Account anotherCopy = accountService.getAccount(account
                    .getAccountId());

            System.out.println("var anotherCopy = " + anotherCopy);

			// make sure these are two separate instances
            // ---- ---- ----- --- --- -------- ---------
            TestCase.assertTrue(account != anotherCopy);

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
     * Test updating of account balance
     */
//    @Test
    public void testUpdateAccountBalance() {

        Account account = null;
        Session session = null;
        AccountService accountService = new AccountService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            System.out.println("var account = " + account);

            account.setBalance(2000);
            accountService.saveOrUpdateAccount(account);
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

            Account anotherCopy = accountService.getAccount(account
                    .getAccountId());
            System.out.println("var anotherCopy = " + anotherCopy);

			// make sure the one we just pulled back
            // from the database has the updated balance
            // -----------------------------------------
            TestCase.assertTrue(anotherCopy.getBalance() == 2000);

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
     * Test updating of detached account
     */
//    @Test
    public void testUpdateDetachedAccount() {
        Account account = null;
        Session session = null;
        AccountService accountService = new AccountService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            System.out.println("var account = " + account);

            accountService.saveOrUpdateAccount(account);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
			// close session -- detaches account
            // ----- ------- -- -------- -------
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

		// update detached account object
        // ------ -------- ------- ------
        account.setBalance(2000);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

			// reattach
            // --------
            accountService.saveOrUpdateAccount(account);

			// commit changes
            // ------ -------
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// make sure the one we just pulled back
            // from the database has the updated balance
            // -----------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Account anotherCopy = accountService.getAccount(account
                    .getAccountId());
            System.out.println("var anotherCopy = " + anotherCopy);

            TestCase.assertTrue(anotherCopy.getBalance() == 2000);

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
     * Test deletion of account
     */
//    @Test
    public void testDeleteAccount() {

        Account account = null;
        Session session = null;
        AccountService accountService = new AccountService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            System.out.println("var account = " + account);

			// delete the account
            // ------ --- -------
            accountService.deleteAccount(account);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        try {
			// try to get the account again -- should be null
            // --- -- --- --- ------- ----- -- ------ -- ----
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Account anotherCopy = accountService.getAccount(account
                    .getAccountId());

            System.out.println("var anotherCopy = " + anotherCopy);

            TestCase.assertNull(anotherCopy);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
			// cleanup
            // -------
            HibernateUtil.getSessionFactory().close();

        }
    }

    /**
     * Test many to many relationship of account-accountOwners. Create two
     * accounts and accountOwners and build associations. Ensure that retrieval
     * of the accountOwners and accounts have the appropriate relationships.
     */
//    @Test
    public void testManyToManyAccountAccountOwners() {
        AccountOwner accountOwner1 = null;
        AccountOwner accountOwner2 = null;
        Account account1 = null;
        Account account2 = null;
        Session session = null;
        AccountService accountService = new AccountService();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

			// Create AccountOwners and Accounts
            // ------ ------------- --- --------
            accountOwner1 = createAccountOwner("111-11-1111");
            accountOwner2 = createAccountOwner("222-22-2222");

            account1 = buildSavingsAccount();
            account1.addAccountOwner(accountOwner1);
            account2 = buildSavingsAccount();
            account2.addAccountOwner(accountOwner1);
            account2.addAccountOwner(accountOwner2);

            accountService.saveOrUpdateAccount(account1);
            accountService.saveOrUpdateAccount(account2);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Load AccountOwner and ensure counts of accounts are correct
            // ---- ------------ --- ------ ------ -- -------- --- -------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            AccountOwnerService accountOwnerService = new AccountOwnerService();
            accountOwner1 = accountOwnerService.getAccountOwner(accountOwner1
                    .getAccountOwnerId());
            accountOwner2 = accountOwnerService.getAccountOwner(accountOwner2
                    .getAccountOwnerId());
            TestCase.assertEquals(2, accountOwner1.getAccounts().size());
            TestCase.assertEquals(1, accountOwner2.getAccounts().size());

			// Load Accounts and ensure counts of AccountOwners are correct
            // ---- -------- --- ------ ------ -- ------------- --- -------
            account1 = accountService.getAccount(account1.getAccountId());
            account2 = accountService.getAccount(account2.getAccountId());
            TestCase.assertEquals(1, account1.getAccountOwners().size());
            TestCase.assertEquals(2, account2.getAccountOwners().size());

            System.out.println(accountOwner1);
            System.out.println(accountOwner2);
            System.out.println(account1);
            System.out.println(account2);
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
            deleteAccount(account1);
//            deleteAccount(account2);
//            deleteAccountOwner(accountOwner1);
//            deleteAccountOwner(accountOwner2);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test creation of multiple ebills for an account.
     */
//    @Test
    public void testCreateMultipleEBills() {
        Session session = null;
        Account account = null;
        AccountService accountService = new AccountService();
        EBillService ebillService = new EBillService();
        long accountId = 0;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            List<EBill> ebills = new ArrayList<EBill>();

			// Create an account
            // ------ -- -------
            account = createCheckingAccount();

			// Create 10 ebills for account. Because this is a set, need
            // some time between the ebills to set a different due date,
            // hence the sleep
            // ---------------------------------------------------------
            for (int i = 0; i < 10; i++) {
                EBill ebill = buildEBill(createEBiller(), account);
                ebills.add(ebill);
                ebillService.saveOrUpdateEBill(ebill);
                sleep(100);
            }
            accountId = account.getAccountId();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

		// Retrieve the persisted account and ensure that the account
        // contains the 10 ebills.
        // ----------------------------------------------------------
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountService = new AccountService();

            account = accountService.getAccount(accountId);
            System.out.println(account);
            TestCase.assertEquals(10, account.getEbills().size());

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
     * Test creation of multiple transactions for an account.
     */
//    @Test
    public void testCreateMultipleAccountTransactions() {
        Session session = null;
        Account account = null;
        long accountId = 0;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            AccountTransactionService accountTransactionService = new AccountTransactionService();
            List<AccountTransaction> accountTransactions = new ArrayList<AccountTransaction>();

			// Create an account
            // ------ -- -------
            account = createCheckingAccount();

			// Create 10 account transactions for account. Because this is a
            // set, need some time between the ebills to set a different due
            // date, hence the sleep
            // ---------------------------------------------------------
            for (int i = 0; i < 10; i++) {
                AccountTransaction accountTransaction = createElectronicAccountTransaction(
                        account, 10);
                accountTransactions.add(accountTransaction);
                accountTransactionService
                        .saveOrUpdateAccountTransaction(accountTransaction);
                sleep(100);
            }
            accountId = account.getAccountId();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
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
     * Test Account Inheritance
     */
//    @Test
    public void testAccountInheritance() {
        Session session = null;
        List<Account> accounts = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

			// Create two checking accounts and three savings accounts
            // ------ --- -------- -------- --- ----- ------- --------
            createCheckingAccount();
            createCheckingAccount();
            createSavingsAccount();
            createSavingsAccount();
            createSavingsAccount();
            createCdAccount();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Retrieve the accounts and confirm that 5 accounts are retrieved,
            // 2 checking accounts, and 3 savings accounts
            // ----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            AccountService accountService = new AccountService();
            accounts = accountService.getAccounts();
            List<CheckingAccount> checkingAccounts = accountService
                    .getCheckingAccounts();
            List<SavingsAccount> savingsAccounts = accountService
                    .getSavingsAccounts();
            List<CdAccount> cdAccounts = accountService.getCdAccounts();

            TestCase.assertEquals(6, accounts.size());
            TestCase.assertEquals(2, checkingAccounts.size());
            TestCase.assertEquals(3, savingsAccounts.size());
            TestCase.assertEquals(1, cdAccounts.size());

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
            for (Account account : accounts) {
                deleteAccount(account);
            }
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test updating of account balance with different flush modes
     */
//    @Test
    public void testUpdateAccountBalanceWithFlushModes() {
        Session session = null;
        Account account = null;
        double originalBalance = 0;
        AccountService accountService = new AccountService();

        try {
			// Flush mode by default is AUTO, so update is changed when
            // transaction commits without an explicit call to save.
            // --------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            System.out.println("var account = " + account);
            originalBalance = account.getBalance();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// make sure the one we just pulled back from the database has
            // the original balance
            // -----------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = accountService.getAccount(account.getAccountId());
            System.out.println(account);

            TestCase.assertTrue(account.getBalance() == originalBalance);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Change flush mode to manual and confirm that update does not
            // occur when transaction commits.
            // -----------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.setFlushMode(FlushMode.MANUAL);
            session.beginTransaction();
            account = accountService.getAccount(account.getAccountId());
            account.setBalance(5000);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// make sure the one we just pulled back from the database does not
            // have the updated balance
            // ----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = accountService.getAccount(account.getAccountId());
            System.out.println(account);
            TestCase.assertTrue(account.getBalance() == originalBalance);

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
     * Test cascade of an account deletion
     */
//    @Test
    public void testAccountDeleteCascade() {
        Session session = null;
        AccountService accountService = new AccountService();
        AccountOwnerService accountOwnerService = new AccountOwnerService();

        Account account = null;
        AccountOwner accountOwner1 = null;
        AccountOwner accountOwner2 = null;
        AccountTransaction accountTransaction = null;

        try {
			// Create Account, AccountOwner, and AccountTransaction and set
            // AccountOwner and AccountTransaction on Account
            // ------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            accountOwner1 = createAccountOwner("111-11-1111");
            accountOwner2 = createAccountOwner("222-22-2222");

            accountTransaction = createElectronicAccountTransaction(account, 10);

            account.addAccountOwner(accountOwner1);
            account.addAccountOwner(accountOwner2);
            account.addAccountTransaction(accountTransaction);

            accountService.saveOrUpdateAccount(account);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Delete the account
            // ------ --- -------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountService.deleteAccount(account);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        try {
			// Retrieve the account and confirm that the account and
            // accountTransaction have been deleted
            // -----------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = accountService.getAccount(account.getAccountId());
            TestCase.assertNull(account);

            AccountTransactionService accountTransactionService = new AccountTransactionService();
            accountTransaction = accountTransactionService
                    .getAccountTransaction(accountTransaction
                            .getAccountTransactionId());
            TestCase.assertNull(accountTransaction);

			// Retrieve the account owner and confirm account owners have
            // not been deleted
            // ----------------------------------------------------------
            accountOwner1 = accountOwnerService.getAccountOwner(accountOwner1
                    .getAccountOwnerId());
            accountOwner2 = accountOwnerService.getAccountOwner(accountOwner2
                    .getAccountOwnerId());

            TestCase.assertNotNull(accountOwner1);
            TestCase.assertNotNull(accountOwner2);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
//            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }

		// Cleanup Account Owners
        // ------- ------- ------
        try {
            deleteAccountOwner(accountOwner1);
            deleteAccountOwner(accountOwner2);
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test Account Transaction Inheritance
     */
//    @Test
    public void testAccountTransactionInheritance() {
        Session session = null;
        Account account = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();

			// Create two physical account transactions and three electronic
            // account transactions. Sleep in between to give different
            // transaction dates.
            // -------------------------------------------------------------
            createPhysicalAccountTransaction(account, 10);
            sleep(100);
            createPhysicalAccountTransaction(account, 20);
            sleep(100);
            createElectronicAccountTransaction(account, 30);
            sleep(100);
            createElectronicAccountTransaction(account, 40);
            sleep(100);
            createElectronicAccountTransaction(account, 50);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Retrieve the account transactions and confirm that 5 accounts are
            // retrieved, 2 physical account transactions, and 3 electronic
            // account transactions.
            //------------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            AccountService accountService = new AccountService();
            account = accountService.getAccount(account.getAccountId());
            SortedSet<AccountTransaction> accountTransactions = account
                    .getAccountTransactions();
            List<ElectronicAccountTransaction> electronicAccountTransactions = new ArrayList<ElectronicAccountTransaction>();
            List<PhysicalAccountTransaction> physicalAccountTransactions = new ArrayList<PhysicalAccountTransaction>();

            for (Iterator<AccountTransaction> iterator = accountTransactions
                    .iterator(); iterator.hasNext();) {
                AccountTransaction accountTransaction = iterator.next();
                if (accountTransaction instanceof ElectronicAccountTransaction) {
                    electronicAccountTransactions
                            .add((ElectronicAccountTransaction) accountTransaction);
                }
                if (accountTransaction instanceof PhysicalAccountTransaction) {
                    physicalAccountTransactions
                            .add((PhysicalAccountTransaction) accountTransaction);
                }

            }

            TestCase.assertEquals(5, accountTransactions.size());
            TestCase.assertEquals(2, physicalAccountTransactions.size());
            TestCase.assertEquals(3, electronicAccountTransactions.size());

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
     * Test Remove Account Transaction With Delete-Orphan
     */
//    @Test
    public void testRemoveAccountTransactionWithDeleteOrphan() {
        Session session = null;
        long accountTransactionId = 0;
        Account account = null;
        AccountTransaction accountTransaction = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = createCheckingAccount();
            accountTransaction = createPhysicalAccountTransaction(account, 10);
            accountTransactionId = accountTransaction.getAccountTransactionId();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Remove transaction from account
            // ------ ----------- ---- -------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account.removeAccountTransaction(accountTransaction);
            AccountService accountService = new AccountService();
            accountService.saveOrUpdateAccount(account);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Get transaction and make sure it's been deleted
            // --- ----------- --- ---- ---- ---- ---- -------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            AccountTransactionService accountTransactionService = new AccountTransactionService();
            accountTransaction = accountTransactionService
                    .getAccountTransaction(accountTransactionId);

            TestCase.assertNull(accountTransaction);

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
     * Test optimistic locking with updating of account balance
     */
//    @Test
    public void testOptimisiticLockingOnUpdateAccountBalance() {
        Session session = null;
        AccountService accountService = new AccountService();
        Account account1 = null;
        Account account = null;
        long accountId = 0;

        try {
			// Create the account and save off the accountId. The version for
            // this account should be 0
            // ---------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            account1 = createCheckingAccount();
            accountId = account1.getAccountId();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Retrieve the account and update it. Now, the version is
            // incremented to 1
            // --------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Account account2 = accountService.getAccount(accountId);
            account2.setBalance(5000);
            accountService.saveOrUpdateAccount(account2);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        boolean reachedException = false;
        try {
			// Update account1 -- since the version numbers do not match should
            // throw StaleStateException
            // ----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            account1.setBalance(2000);
            accountService.saveOrUpdateAccount(account1);
            session.getTransaction().commit();
        } catch (StaleObjectStateException e) {
            session.getTransaction().rollback();
            reachedException = true;
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        TestCase.assertTrue(reachedException);

        try {
			// Retrieve the account and ensure that the account has the balance
            // from the successful update
            // ----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            account = accountService.getAccount(accountId);
            System.out.println(account);
            TestCase.assertTrue(account.getBalance() == 5000);
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
