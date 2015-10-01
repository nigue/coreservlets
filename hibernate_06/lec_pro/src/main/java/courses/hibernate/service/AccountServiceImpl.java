package courses.hibernate.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;

/**
 * Stateless EJB Service implementation for Account
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountServiceImpl implements AccountServiceRemote {
	@Resource
	private UserTransaction userTransaction;

	AccountDAO accountDAO = new AccountDAO();

	public static final String REMOTE_JNDI_NAME = AccountServiceImpl.class
			.getSimpleName()
			+ "/remote";

	/**
	 * {@inheritDoc}
	 */
	public Account saveOrUpdateAccount(Account account) {
		try {
			userTransaction.begin();
			account = accountDAO.saveOrUpdateAccount(account);
			userTransaction.commit();
		}
		// Catches HibernateException and SystemException
		catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (SystemException se) {
			}
		}
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteAccount(Account account) {
		try {
			// Obtain and start transaction
			userTransaction.begin();
			accountDAO.deleteAccount(account);
			userTransaction.commit();
		}
		// Catches HibernateException and SystemException
		catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (SystemException se) {
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Account getAccount(long accountId) {
		Account account = null;
		try {
			userTransaction.begin();
			account = accountDAO.getAccount(accountId);
			userTransaction.commit();
		}
		// Catches HibernateException and SystemException
		catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (SystemException se) {
			}
		}
		return account;
	}
}
