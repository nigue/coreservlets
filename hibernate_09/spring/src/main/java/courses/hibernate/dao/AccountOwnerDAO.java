package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import courses.hibernate.vo.AccountOwner;

/**
 * Data Access Object for Account Owner
 */
public class AccountOwnerDAO {

	private SessionFactory sessionFactory;

	/**
	 * Constructor to create AccountOwnerDAO. Used by Spring
	 * 
	 * @param sessionFactory
	 *            Hibernate session factory
	 */
	public AccountOwnerDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Create a new account owner or update an existing one
	 * 
	 * @param accountOwner
	 *            account owner to be persisted
	 */
	public void saveOrUpdateAccountOwner(AccountOwner accountOwner) {
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
		session.delete(accountOwner);
	}

	/**
	 * Retrieve an account owner from the data store by lastName and SSN using
	 * HQL
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
		Session session = sessionFactory.getCurrentSession();
		return (List<AccountOwner>) session
				.createQuery(
						"from AccountOwner ao where "
								+ "ao.lastName = :lastName and ao.socialSecurityNumber = :ssn")
				.setParameter("lastName", lastName).setParameter("ssn",
						socialSecurityNumber).list();
	}
}
