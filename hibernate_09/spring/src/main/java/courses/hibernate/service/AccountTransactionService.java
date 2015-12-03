package courses.hibernate.service;

import courses.hibernate.dao.AccountTransactionDAO;
import courses.hibernate.vo.AccountTransaction;

/**
 * Service layer for Account Transaction
 */
public class AccountTransactionService {

	AccountTransactionDAO accountTransactionDAO;

	/**
	 * Set accountTransactionDAO
	 * 
	 * @param accountTransactionDAO
	 *            accountTransactionDAO to set. Used by Spring.
	 */
	public void setAccountTransactionDAO(
			AccountTransactionDAO accountTransactionDAO) {
		this.accountTransactionDAO = accountTransactionDAO;
	}

	/**
	 * Create a new account transaction or update an existing one
	 * 
	 * @param accountTransaction
	 *            account transaction to be persisted
	 */
	public void saveOrUpdateAccountTransaction(
			AccountTransaction accountTransaction) {
		accountTransactionDAO
				.saveOrUpdateAccountTransaction(accountTransaction);
	}

	/**
	 * Retrieve an account transaction
	 * 
	 * @param accountTransactionId
	 *            identifier of the account transaction to be retrieved
	 * @return accountTransaction represented by the identifier provided
	 */
	public AccountTransaction getAccountTransaction(long accountTransactionId) {
		return accountTransactionDAO
				.getAccountTransaction(accountTransactionId);
	}
}
