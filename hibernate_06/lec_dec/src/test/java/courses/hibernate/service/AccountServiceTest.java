package courses.hibernate.service;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.TestCase;

import org.junit.Test;

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
     * Get a handle to the remove AccountService
     *
     * @return reference to AccountServiceRemote EJB
     */
    private AccountServiceRemote getAccountService() {
        Properties properties = new Properties();
        properties.put("java.naming.factory.initial",
                "org.jnp.interfaces.NamingContextFactory");
        properties.put("java.naming.factory.url.pkgs",
                "=org.jboss.naming:org.jnp.interfaces");
        properties.put("java.naming.provider.url", "localhost:1099");
        Context context;
        try {
            context = new InitialContext(properties);
            AccountServiceRemote accountService = (AccountServiceRemote) context
                    .lookup(AccountServiceImpl.REMOTE_JNDI_NAME);
            return accountService;
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
