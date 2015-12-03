package courses.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import courses.hibernate.vo.AccountTransaction;

/**
 * Data Access Object for Account Transaction
 */
public class AccountTransactionDAO {

	private SessionFactory sessionFactory;

	/**
	 * Constructor to create AccountTransactionDAO. Used by Spring
	 * 
	 * @param sessionFactory
	 *            Hibernate session factory
	 */
	public AccountTransactionDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Create a new account transaction or update an existing one
	 * 
	 * @param accountTransaction
	 *            account transaction to be persisted
	 */
	public void saveOrUpdateAccountTransaction(
			AccountTransaction accountTransaction) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(accountTransaction);
	}

	/**s
	 * Retrieve an account transaction from the data store
	 * 
	 * @param accountTransactionId
	 *            identifier of the account transaction to be retrieved
	 * @return accountTransaction represented by the identifier provided
	 */
	public AccountTransaction getAccountTransaction(long accountTransactionId) {
		Session session = sessionFactory.getCurrentSession();
		return (AccountTransaction) session.get(AccountTransaction.class,
				accountTransactionId);
	}
}
