package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountOwner;

/**
 * Data Access Object for Account Owner
 */
public class AccountOwnerDAO {

	/**
	 * Retrieve an account owner from the data store by lastName and SSN using
	 * HQL
	 * 
	 * **************************************************************
	 * SOLUTION FOR EXERCISE 8:EASY
	 * PART 1 of 1
	 * Use the Hibernate Query Language to retrieve the ‘MINNIE MOUSE’ 
	 * Account Owner record by using just her last name and SSN
	 * **************************************************************	 
     *	 
	 * @param lastName
	 *            last name of account owner
	 * @param socialSecurityNumber
	 *            ssn of account owner
	 * @return accountOwner represented by last name and social security number
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwnersWithHQL(String lastName,
			String socialSecurityNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session
				.createQuery(
						"from AccountOwner ao where "
								+ "ao.lastName = :lastName and ao.socialSecurityNumber = :ssn")
				.setParameter("lastName", lastName).setParameter("ssn",
						socialSecurityNumber).list();
	}

	
	/**
	 * Create a new account owner or update an existing one
	 * 
	 * @param accountOwner
	 *            account owner to be persisted
	 */
	public void saveOrUpdateAccountOwner(AccountOwner accountOwner) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(accountOwner);
	}

	/**
	 * Retrieve an account owner from the data store
	 * 
	 * @param accountOwnerId
	 *            identifier of the account owner to be retrieved
	 * @return accountOwner represented by the identifier provided
	 */
	public AccountOwner getAccountOwner(long accountOwnerId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		AccountOwner accountOwner = (AccountOwner) session.get(
				AccountOwner.class, accountOwnerId);
		return accountOwner;
	}

	/**
	 * Delete account owner from data store
	 * 
	 * @param accountOwner
	 *            accountOwner to be deleted
	 */
	public void deleteAccountOwner(AccountOwner accountOwner) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(accountOwner);
	}
}
