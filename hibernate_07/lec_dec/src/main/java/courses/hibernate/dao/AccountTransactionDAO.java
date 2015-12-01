package courses.hibernate.dao;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountTransaction;

/**
 * Data Access Object for Account Transaction
 */
public class AccountTransactionDAO {

	/**
	 * Create a new account transaction or update an existing one
	 * 
	 * @param accountTransaction
	 *            account transaction to be persisted
	 */
	public void saveOrUpdateAccountTransaction(
			AccountTransaction accountTransaction) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(accountTransaction);
	}

	/**
	 * Retrieve an account transaction from the data store
	 * 
	 * @param accountTransactionId
	 *            identifier of the account transaction to be retrieved
	 * @return accountTransaction represented by the identifier provided
	 */
	public AccountTransaction getAccountTransaction(long accountTransactionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (AccountTransaction) session.get(AccountTransaction.class,
				accountTransactionId);
	}
}
