package courses.hibernate.service;

import javax.ejb.Remote;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

/**
 * Remote service interface for Account
 */
@Remote
public interface AccountServiceRemote {
	AccountDAO accountDAO = new AccountDAO();

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account
	 *            account to be persisted
	 */
	public Account saveOrUpdateAccount(Account account);

	/**
	 * Delete account
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account);
	
	/**
	 * Retrieve an account
	 * 
	 * @param accountId
	 *            identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId);

}
