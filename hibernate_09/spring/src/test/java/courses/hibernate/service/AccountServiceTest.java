package courses.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest extends ServiceTest {

    /**
     * Test account creation
     */
    @Test
    public void testCreateAccount() {

        SavingsAccount account = new SavingsAccount();
        account.setBalance(1000L);
        account.setInterestRate(3.5);

        TestCase.assertTrue(account.getAccountId() == 0);

		// save the account
        // ---- --- -------
        accountService.saveOrUpdateAccount(account);

        System.out.println("var account = " + account);

		// check that IDs were set after the hbm session
        // ----- ---- --- ---- --- ----- --- --- -------
        TestCase.assertTrue(account.getAccountId() > 0);

		// check that interceptor set creation date
        // ----- ---- ----------- --- -------- ----
        TestCase.assertNotNull(account.getCreationDate());

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test retrieval of account
     */
    //@Test
    public void testGetAccount() {
        Account account = createSavingsAccount();
        System.out.println("var account = " + account);

        Account anotherCopy = accountService.getAccount(account.getAccountId());

        System.out.println("var anotherCopy = " + anotherCopy);

		// make sure these are two separate instances
        // ---- ---- ----- --- --- -------- ---------
        TestCase.assertTrue(account != anotherCopy);

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test updating of account balance
     */
    //@Test
    public void testUpdateAccountBalance() {

        Account account = createCheckingAccount();
        System.out.println("var account = " + account);

		// Confirm creation date is not null and updateDate is null
        // ------- -------- ---- -- --- ---- --- ---------- -- ----
        TestCase.assertNotNull(account.getCreationDate());
        TestCase.assertNull(account.getUpdateDate());

        account.setBalance(2000);
        accountService.saveOrUpdateAccount(account);

        Account anotherCopy = accountService.getAccount(account.getAccountId());
        System.out.println("var anotherCopy = " + anotherCopy);

		// make sure the one we just pulled back
        // from the database has the updated balance
        // -----------------------------------------
        TestCase.assertTrue(anotherCopy.getBalance() == 2000);

		// Confirm both dates are not null
        // ------- ---- ----- --- --- ----
        TestCase.assertNotNull(anotherCopy.getCreationDate());
        TestCase.assertNotNull(anotherCopy.getUpdateDate());

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test deletion of account
     */
    //@Test
    public void testDeleteAccount() {

        Account account = createCheckingAccount();
        System.out.println("var account = " + account);

		// delete the account
        // ------ --- -------
        accountService.deleteAccount(account);

        Account anotherCopy = accountService.getAccount(account.getAccountId());
        System.out.println("var anotherCopy = " + anotherCopy);

        TestCase.assertNull(anotherCopy);
    }

    /**
     * Test add interest to savings accounts - batch update test
     */
    //@Test
    public void testAddInterestToSavingsAccounts() {

        List<SavingsAccount> savingsAccounts = accountService
                .getSavingsAccounts();
        int numberOfSavingsAccounts = savingsAccounts.size();

        int numberOfAccountsUpdated = accountService
                .addInterestToSavingsAccounts();
        TestCase.assertEquals(numberOfSavingsAccounts, numberOfAccountsUpdated);
    }

    /**
     * Test add interest to savings accounts - batch update test
     */
    //@Test
    public void testArchiveAccounts() {

        List<Account> accounts = accountService.getAccounts();
        int numberOfAccounts = accounts.size();

        int numberOfAccountsInserted = accountService.archiveAccounts();
        TestCase.assertEquals(numberOfAccounts, numberOfAccountsInserted);

        int numberOfAccountDeleted = accountService.deleteArchivedAccounts();
        TestCase.assertEquals(numberOfAccounts, numberOfAccountDeleted);
    }

    /**
     * Test many to many relationship of account-accountOwners. Create two
     * accounts and accountOwners and build associations. Ensure that retrieval
     * of the accountOwners and accounts have the appropriate relationships.
     */
    //@Test
    public void testManyToManyAccountAccountOwners() {
        AccountOwner accountOwner1 = null;
        AccountOwner accountOwner2 = null;
        Account account1 = null;
        Account account2 = null;

		// Create AccountOwners and Accounts
        // ------ ------------- --- --------
        accountOwner1 = createAccountOwner("123-45-6789");
        accountOwner2 = createAccountOwner("987-65-4321");

        account1 = buildSavingsAccount();
        account1.addAccountOwner(accountOwner1);
        account2 = buildSavingsAccount();
        account2.addAccountOwner(accountOwner1);
        account2.addAccountOwner(accountOwner2);

        accountService.saveOrUpdateAccount(account1);
        accountService.saveOrUpdateAccount(account2);

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

		// cleanup
        // -------
        deleteAccount(account1);
        deleteAccount(account2);
        deleteAccountOwner(accountOwner1);
        deleteAccountOwner(accountOwner2);
    }

    /**
     * Test creation of multiple ebills for an account.
     */
    //@Test
    public void testCreateMultipleEBills() {
        Account account = null;
        long accountId = 0;

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

		// Retrieve the persisted account and ensure that the account
        // contains the 10 ebills.
        // ----------------------------------------------------------
        account = accountService.getAccount(accountId);
        System.out.println(account);
        TestCase.assertEquals(10, account.getEbills().size());

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test creation of multiple transactions for an account.
     */
    //@Test
    public void testCreateMultipleAccountTransactions() {
        Account account = null;
        long accountId = 0;

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
		// Retrieve the persisted account and ensure that the account
        // contains the 10 transactions.
        // ----------------------------------------------------------
        account = accountService.getAccount(accountId);
        System.out.println(account);
        TestCase.assertEquals(10, account.getAccountTransactions().size());
        TestCase.assertEquals(new Double(1000), account.getBalance());

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test cascade of an account deletion
     */
    //@Test
    public void testAccountDeleteCascade() {
        Account account = null;
        AccountOwner accountOwner1 = null;
        AccountOwner accountOwner2 = null;
        AccountTransaction accountTransaction = null;

		// Create Account, AccountOwner, and AccountTransaction and set
        // AccountOwner and AccountTransaction on Account
        // ------------------------------------------------------------
        account = createCheckingAccount();
        accountOwner1 = createAccountOwner("123-45-6789");
        accountOwner2 = createAccountOwner("987-65-4321");

        accountTransaction = createElectronicAccountTransaction(account, 10);

        account.addAccountOwner(accountOwner1);
        account.addAccountOwner(accountOwner2);
        account.addAccountTransaction(accountTransaction);

        accountService.saveOrUpdateAccount(account);

		// Delete the account
        // ------ --- -------
        accountService.deleteAccount(account);

		// Retrieve the account and confirm that the account and
        // accountTransaction have been deleted
        // -----------------------------------------------------
        account = accountService.getAccount(account.getAccountId());
        TestCase.assertNull(account);

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
		// Cleanup Account Owners
        // ------- ------- ------
        deleteAccountOwner(accountOwner1);
        deleteAccountOwner(accountOwner2);
    }

}
