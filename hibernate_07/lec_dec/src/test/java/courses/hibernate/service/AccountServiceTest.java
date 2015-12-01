package courses.hibernate.service;

import java.util.Date;
import java.util.Properties;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.TestCase;

import org.junit.Test;

import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer tests for Account
 */
public class AccountServiceTest {

    /**
     * Test account creation
     */
    @Test
    public void testCreateAccount() {
        AccountServiceRemote accountService = getAccountService();

        SavingsAccount account = new SavingsAccount();
        account.setCreationDate(new Date());
        account.setBalance(1000L);
        account.setInterestRate(3.5);

        TestCase.assertTrue(account.getAccountId() == 0);

        // create the account
        // ------ --- -------
        account = (SavingsAccount) accountService.saveOrUpdateAccount(account);

        // check that IDs were set after the hbm session
        // ----- ---- --- ---- --- ----- --- --- -------
        TestCase.assertTrue(account.getAccountId() > 0);
        long accountId = account.getAccountId();

        // retrieve the account
        // -------- --- -------
        account = (SavingsAccount) accountService.getAccount(accountId);
        TestCase.assertNotNull(account);
        TestCase.assertNotNull(account.getAccountOwners());

        // cleanup
        // -------
        accountService.deleteAccount(account);
    }

    /**
     * Test fail transfer funds
     */
    @Test
    public void testFailTransferFunds() {
        AccountServiceRemote accountService = getAccountService();

        CheckingAccount account1 = new CheckingAccount();
        account1.setCreationDate(new Date());
        account1.setBalance(1000.00);
        account1 = (CheckingAccount) accountService
                .saveOrUpdateAccount(account1);

        CheckingAccount account2 = new CheckingAccount();
        account2.setCreationDate(new Date());
        account2.setBalance(2000.00);
        account2 = (CheckingAccount) accountService
                .saveOrUpdateAccount(account2);

        // Confirm that we hit the exception
        // ------- ---- -- --- --- ---------
        boolean reachedException = false;
        try {
            accountService.failTransferFunds(account2, account1, 100L);
        } catch (EJBException e) {
            e.printStackTrace();
            reachedException = true;
        }
        TestCase.assertTrue(reachedException);

        // retrieve the accounts -- confirm that updates were not successful
        // -------- --- -------- -- ------- ---- ------- ---- --- ----------
        account1 = (CheckingAccount) accountService.getAccount(account1
                .getAccountId());
        account2 = (CheckingAccount) accountService.getAccount(account2
                .getAccountId());

        TestCase.assertEquals(1000.00, account1.getBalance());
        TestCase.assertEquals(2000.00, account2.getBalance());

        // cleanup
        // -------
        accountService.deleteAccount(account1);
        accountService.deleteAccount(account2);

    }

    /**
     * Get a handle to the remove AccountService
     *
     * @return reference to AccountServiceRemote EJB
     */
    private AccountServiceRemote getAccountService() {
        Properties properties = new Properties();
        properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
        properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
        properties.put("java.naming.provider.url", "localhost:1099");
        Context context;
        try {
            context = new InitialContext(properties);
            AccountServiceRemote accountService = (AccountServiceRemote) context.lookup(AccountServiceImpl.REMOTE_JNDI_NAME);
            return accountService;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
