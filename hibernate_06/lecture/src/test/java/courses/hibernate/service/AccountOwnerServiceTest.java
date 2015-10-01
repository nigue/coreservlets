package courses.hibernate.service;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;

/**
 * Service layer tests for AccountOwner
 */
public class AccountOwnerServiceTest extends ServiceTest {

    /**
     * Test transactions with select identity generator -- will only work
     * against Oracle database. Please uncomment annotation before testing.
     */
    // @Test
    public void testClosingSessionWithoutEndingTransaction() {
        Session session = null;
        AccountOwner accountOwner = null;
        Account account = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        AccountService accountService = new AccountService();

        long accountOwnerId = 0;
        long accountId = 0;

        try {
			// Begin transaction and create account owner and account. Since
            // accountOwner has a select identity generator which causes the
            // insert to occur immediately to obtain the primary key id value.
            // Account on the other hand, uses a native identity generator,
            // where a simple select is used to obtain the new identifier.
            // ---------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountOwner = buildAccountOwner("111-11-1111");
            accountOwnerService.saveOrUpdateAccountOwner(accountOwner);
            accountOwnerId = accountOwner.getAccountOwnerId();

            account = buildCheckingAccount();
            accountService.saveOrUpdateAccount(account);
            accountId = account.getAccountId();

			// Do not end transaction
            // -- --- --- -----------
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Verify that since the transaction was not ended in the case
            // above, the insert of the account owner for the select identity
            // generator is committed. Since insert to account was never made,
            // it is not persisted.
            // ---------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountOwner = accountOwnerService.getAccountOwner(accountOwnerId);
            TestCase.assertNotNull(accountOwner);

            account = accountService.getAccount(accountId);
            TestCase.assertNull(account);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        deleteAccountOwner(accountOwner);

        try {

			// Begin transaction and create both account owner and account. This
            // time, rollback transaction
            // -----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            account = buildCheckingAccount();
            accountService.saveOrUpdateAccount(account);
            accountId = account.getAccountId();

            accountOwner = buildAccountOwner("111-11-1111");
            accountOwnerService.persistAccountOwner(accountOwner);
            accountOwnerId = accountOwner.getAccountOwnerId();

            session.getTransaction().rollback();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Since transaction was rolled back in the previous step, both
            // account owner and account should be null -- The insert resulting
            // from the select identity generator is rolled back.
            // ----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountOwner = accountOwnerService.getAccountOwner(accountOwnerId);
            TestCase.assertNull(accountOwner);

            account = accountService.getAccount(accountId);
            TestCase.assertNull(account);

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
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test adding of account owners to account using load method and proxies
     */
    @Test
    public void testTransactionRollbackAgainstSSNUniqueKey() {
        AccountOwner accountOwner1 = null;
        AccountOwner accountOwner2 = null;

        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();

        long accountOwner1Id = 0;
        long accountOwner2Id = 0;
        try {
			// Create two account owners with the same ssn - should result
            // in database unique key violation, which will rollback the
            // transaction. So, neither account owner should be persisted
            // in the database.
            // -----------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountOwner1 = createAccountOwner("111-11-1111");
            accountOwner1Id = accountOwner1.getAccountOwnerId();
            accountOwner2 = createAccountOwner("111-11-1111");
            accountOwner2Id = accountOwner2.getAccountOwnerId();

            session.getTransaction().commit();
            TestCase.fail();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
        try {
			// Validate that neither account owner was persisted in the
            // database.
            // --------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            accountOwner1 = accountOwnerService
                    .getAccountOwner(accountOwner1Id);
            TestCase.assertNull(accountOwner1);
            accountOwner2 = accountOwnerService
                    .getAccountOwner(accountOwner2Id);
            TestCase.assertNull(accountOwner2);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }
    }
}
