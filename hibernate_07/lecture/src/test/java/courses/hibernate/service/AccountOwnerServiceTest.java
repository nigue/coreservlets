package courses.hibernate.service;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;

/**
 * Service layer tests for AccountOwner
 */
public class AccountOwnerServiceTest extends ServiceTest {

    /**
     * Test optimistic locking with updating of account owner cell phone
     */
    @Test
    public void testOptimisiticLockingOnUpdateAccountOwnerCellPhone() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        AccountOwner accountOwner1 = null;
        AccountOwner accountOwner = null;
        long accountOwnerId = 0;

        try {
            // Create the account owner and save off the accountOwnerId. The
            // version for this accountOwner should be 0
            // ---------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            accountOwner1 = createAccountOwner("123-45-6789");
            accountOwnerId = accountOwner1.getAccountOwnerId();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        try {
			// Retrieve the account owner and update it. Now, the version is
            // incremented to 1
            // -------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            AccountOwner accountOwner2 = accountOwnerService
                    .getAccountOwner(accountOwnerId);
            accountOwner2.setCellPhone("123-456-7890");
            accountOwnerService.saveOrUpdateAccountOwner(accountOwner2);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

        boolean reachedException = false;
        try {
			// Update accountOwner1 -- since the version numbers do not match
            // should throw StaleStateException
            // --------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            accountOwner1.setCellPhone("098-765-4321");
            accountOwnerService.saveOrUpdateAccountOwner(accountOwner1);
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
			// Retrieve the account owner and ensure that the account has the
            // balance from the successful update
            // ----------------------------------------------------------------
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            accountOwner = accountOwnerService.getAccountOwner(accountOwnerId);
            System.out.println(accountOwner);
            TestCase.assertTrue(accountOwner.getCellPhone()
                    .equals("123-456-7890"));
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
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- balanceMinimum
     */
    //@Test
    public void testGetAccountsByBalanceMinimum() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService.getAccountOwners(3000L);
            TestCase.assertEquals(3, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                for (Account account : accountOwner.getAccounts()) {
                    TestCase.assertTrue(account.getBalance() > 3000.00);
                }
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwner -- using restrictions
     */
    //@Test
    public void testGetAccountOwnerUsingRestrictions() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            AccountOwner accountOwner = accountOwnerService
                    .getAccountOwnerUsingRestrictions("martyhall@coreservlets.com");
            TestCase.assertEquals(accountOwner.getEmail(),
                    "martyhall@coreservlets.com");
            System.out.println(accountOwner);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwner -- using property
     */
    //@Test
    public void testGetAccountOwnerUsingProperty() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            AccountOwner accountOwner = accountOwnerService
                    .getAccountOwnerUsingProperty("martyhall@coreservlets.com");
            TestCase.assertEquals(accountOwner.getEmail(),
                    "martyhall@coreservlets.com");
            System.out.println(accountOwner);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- zip code
     */
    //@Test
    public void testGetAccountOwnersByZipCode() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwners("12345");
            TestCase.assertEquals(3, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getAddress().getZipCode()
                        .getZip().equals("12345"));
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- first name/last name prefix
     */
    //@Test
    public void testGetAccountOwnersByFirstNameLastNamePrefix() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwners("M", "H");

            TestCase.assertEquals(2, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getFirstName().startsWith("M"));
                TestCase.assertTrue(accountOwner.getLastName().startsWith("H"));
            }

            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- using or
     */
    //@Test
    public void testGetAccountOwnersUsingOr() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwnersUsingOr("M", "H", "coreservlets.com");
            TestCase.assertEquals(3, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                if (!accountOwner.getEmail().endsWith("coreservlets.com")) {
                    TestCase.assertTrue(accountOwner.getFirstName().startsWith(
                            "M"));
                    TestCase.assertTrue(accountOwner.getLastName()
                            .startsWith("H"));
                }
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- using junctions
     */
    //@Test
    public void testGetAccountOwnersUsingJunctions() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwnersUsingJunctions("M", "H",
                            "coreservlets.com");
            TestCase.assertEquals(3, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                if (!accountOwner.getEmail().endsWith("coreservlets.com")) {
                    TestCase.assertTrue(accountOwner.getFirstName().startsWith(
                            "M"));
                    TestCase.assertTrue(accountOwner.getLastName()
                            .startsWith("H"));
                }
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- detachedCriteria
     */
    //@Test
    public void testGetAccountOwnersByDetachedCriteria() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
                    AccountOwner.class).add(
                            Restrictions.like("lastName", "M%").ignoreCase());
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwners(detachedCriteria);
            TestCase.assertEquals(2, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getLastName().startsWith("M"));
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- query by example
     */
    //@Test
    public void testGetAccountOwnersUsingExampleAccountOwner() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            AccountOwner exampleAccountOwner = new AccountOwner();
            exampleAccountOwner.setLastName("MOUSE");
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwners(exampleAccountOwner);
            TestCase.assertEquals(2, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getLastName().equals("MOUSE"));
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Test getAccountOwners -- query by example and query by criteria on
     * zipcode
     */
    //@Test
    public void testGetAccountOwnersUsingExampleAccountOwnerAndZipCode() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            AccountOwner exampleAccountOwner = new AccountOwner();
            exampleAccountOwner.setLastName("MOUSE");
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwners(exampleAccountOwner, "12345");
            TestCase.assertEquals(1, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getLastName().equals("MOUSE"));
                TestCase.assertTrue(accountOwner.getAddress().getZipCode()
                        .getZip().equals("12345"));
            }
            System.out.println(accountOwners);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            TestCase.fail();
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            HibernateUtil.getSessionFactory().close();
        }
    }

}
