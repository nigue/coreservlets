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
	 * SOLUTION FOR EXERCISE 6:MEDIUM
	 * Uses declarative transaction management to attempt to transfer
	 * funds between two accounts, and then conciously throws an
	 * exception in order to test to make sure the transaction rolls
	 * back.
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void failTransferFunds(Account fromAccount, Account toAccount,
			double amount) {
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		accountDAO.saveOrUpdateAccount(fromAccount);
		toAccount.setBalance(toAccount.getBalance() + amount);
		accountDAO.saveOrUpdateAccount(toAccount);
		throw new RuntimeException();
	}

	
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
