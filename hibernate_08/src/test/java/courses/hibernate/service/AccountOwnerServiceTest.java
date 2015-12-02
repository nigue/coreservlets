package courses.hibernate.service;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountOwner;

/**
 * Service layer tests for AccountOwner
 */
public class AccountOwnerServiceTest extends ServiceTest {

    /**
     * Test getAccountOwner by LastName And SSN Using QBE
     */
//    @Test
    public void testGetAccountOwnerByLastNameAndSSNUsingQBE() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            AccountOwner exampleAccountOwner = new AccountOwner();
            exampleAccountOwner.setLastName("MOUSE");
            exampleAccountOwner.setSocialSecurityNumber("444-44-4444");

            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwners(exampleAccountOwner);
            TestCase.assertEquals(1, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getLastName().equals("MOUSE"));
                TestCase.assertTrue(accountOwner.getSocialSecurityNumber().equals("444-44-4444"));
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
     * Test getAccountOwnersUsingGlobalQuery
     */
//    @Test
    public void testGetAccountOwnersUsingGlobalQuery() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwnersUsingGlobalQuery();
            TestCase.assertEquals(6, accountOwners.size());
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
     * Test getAccountOwnersUsingClassQuery
     */
//    @Test
    public void testGetAccountOwnersUsingClassQuery() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwnersUsingClassQuery("12345");

            TestCase.assertEquals(3, accountOwners.size());
            for (AccountOwner accountOwner : accountOwners) {
                TestCase.assertTrue(accountOwner.getAddress().getZipCode().getZip().equals("12345"));
            }
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
     * Test getAccountOwnersWithFormattedNames
     */
//    @Test
    public void testGetAccountOwnersWithFormattedNames() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<String> accountOwnerNames = accountOwnerService
                    .getAccountOwnersWithFormattedNames();

            TestCase.assertEquals(6, accountOwnerNames.size());
            System.out.println(accountOwnerNames);
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
     * Test getAccountOwnersUsingThetaJoin
     */
//    @Test
    public void testGetAccountOwnersUsingThetaJoin() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwnersUsingThetaJoin("VISA");
            TestCase.assertEquals(1, accountOwners.size());
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
