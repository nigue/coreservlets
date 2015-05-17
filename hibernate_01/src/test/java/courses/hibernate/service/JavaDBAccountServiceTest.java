package courses.hibernate.service;

import java.util.Date;
import org.junit.Test;
import courses.hibernate.service.AccountService.DatabaseType;
import courses.hibernate.vo.Account;
import junit.framework.TestCase;

public class JavaDBAccountServiceTest {

    /**
     * Get the account service
     *
     * @return account service for java db
     */
    protected AccountService getAccountService() {
        return new AccountService(DatabaseType.JAVADB);
    }

    /**
     * Test account creation
     */
    @Test
    public void testCreateAccount() {
		// create the account
        // ------ --- -------
        Account account = new Account();
        account.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
        account.setCreationDate(new Date());
        account.setBalance(1000L);

        TestCase.assertTrue(account.getAccountId() == 0);

		// save the account
        // ---- --- -------
        AccountService accountService = getAccountService();
        account = accountService.createAccount(account);

        System.out.println("var account = " + account);

		// check that IDs were set after the hbm session
        // ----- ---- --- ---- --- ----- --- --- -------
        TestCase.assertTrue(account.getAccountId() > 0);

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test retrieval of account
     */
    @Test
    public void testGetAccount() {
        Account account = createAccount();
        System.out.println("var account = " + account);

        AccountService accountService = getAccountService();
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
     * Test deletion of account
     */
    @Test
    public void testDeleteAccount() {
        Account account = createAccount();
        System.out.println("var account = " + account);

		// delete the account
        // ------ --- -------
        AccountService accountService = getAccountService();
        accountService.deleteAccount(account);

		// try to get the account again -- should be null
        // --- -- --- --- ------- ----- -- ------ -- ----
        Account anotherCopy = accountService.getAccount(account.getAccountId());

        System.out.println("var delete anotherCopy = " + anotherCopy);

        TestCase.assertNull(anotherCopy);
    }

    /**
     * Test update of account type. Account Type is set to update=false in
     * Hibernate Mapping. Therefore, ensure that it does not get updated.
     */
    @Test
    public void testUpdateAccountType() {
        Account account = createAccount();
        System.out.println("var account = " + account);

        AccountService accountService = getAccountService();
        account.setAccountType(Account.ACCOUNT_TYPE_CHECKING);
        accountService.updateAccount(account);

        Account anotherCopy = accountService.getAccount(account.getAccountId());
        System.out.println("var update anotherCopy = " + anotherCopy);

		// make sure the one we just pulled back from
        // the database DOES NOT HAVE the updated balance
        // ----------------------------------------------
        TestCase.assertFalse(anotherCopy.getAccountType().equals(
                Account.ACCOUNT_TYPE_CHECKING));

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Test updating of account balance
     */
    @Test
    public void testUpdateAccountBalance() {
        Account account = createAccount();
        System.out.println("var account = " + account);

        AccountService accountService = getAccountService();
        account.setBalance(2000);
        accountService.updateAccount(account);

        Account anotherCopy = accountService.getAccount(account.getAccountId());
        System.out.println("var update balance anotherCopy = " + anotherCopy);

		// make sure the one we just pulled back
        // from the database has the updated balance
        // -----------------------------------------
        TestCase.assertTrue(anotherCopy.getBalance() == 2000);

		// cleanup
        // -------
        deleteAccount(account);
    }

    /**
     * Build an account
     *
     * @return account
     */
    private Account buildAccount() {
        Account account = new Account();
        account.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
        account.setCreationDate(new Date());
        account.setBalance(1000L);
        return account;
    }

    /**
     * Create an account
     *
     * @return account that was created
     */
    private Account createAccount() {
        AccountService accountService = getAccountService();
        Account account = buildAccount();
        account = accountService.createAccount(account);
        return account;
    }

    /**
     * Delete an account
     *
     * @param account account to be deleted
     */
    private void deleteAccount(Account account) {
        AccountService accountService = getAccountService();
        accountService.deleteAccount(account);
    }

}
