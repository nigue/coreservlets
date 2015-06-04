package courses.hibernate.dao;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountOwner;

/**
 * Data Access Object for Account Owner
 */
public class AccountOwnerDAO {

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
