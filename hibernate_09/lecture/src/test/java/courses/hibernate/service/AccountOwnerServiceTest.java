package courses.hibernate.service;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Test;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountOwner;

/**
 * Service layer tests for AccountOwner
 */
public class AccountOwnerServiceTest extends ServiceTest {

    @AfterClass
    public static void closeHibernateSessionFactory() {
        HibernateUtil.getSessionFactory().close();
    }

    /**
     * Test getAccountOwner by LastName And SSN Using HQL
     */
    @Test
    public void testGetAccountOwnerByLastNameAndSSNUsingHQL() {
        Session session = null;
        AccountOwnerService accountOwnerService = new AccountOwnerService();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<AccountOwner> accountOwners = accountOwnerService
                    .getAccountOwnersWithHQL("MOUSE", "444-44-4444");
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
        }
    }

}
