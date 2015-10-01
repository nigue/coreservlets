package courses.hibernate.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

/**
 * Stateless EJB Service implementation for Account
 */
@Stateless
public class AccountServiceImpl implements AccountServiceRemote {
	AccountDAO accountDAO = new AccountDAO();

	public static final String REMOTE_JNDI_NAME = AccountServiceImpl.class
			.getSimpleName()
			+ "/remote";

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account saveOrUpdateAccount(Account account) {
		accountDAO.saveOrUpdateAccount(account);
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAccount(Account account) {
		accountDAO.deleteAccount(account);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account getAccount(long accountId) {
		return accountDAO.getAccount(accountId);
	}
}
