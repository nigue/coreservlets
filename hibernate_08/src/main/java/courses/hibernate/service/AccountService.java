package courses.hibernate.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.CdAccount;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBiller;
import courses.hibernate.vo.EBillerRegistration;
import courses.hibernate.vo.SavingsAccount;

/**
 * Service layer for Account
 */
public class AccountService {
	AccountDAO accountDAO = new AccountDAO();

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account
	 *            account to be persisted
	 */
	public void saveOrUpdateAccount(Account account) {
		accountDAO.saveOrUpdateAccount(account);
	}

	/**
	 * Retrieve an account
	 * 
	 * @param accountId
	 *            identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		return accountDAO.getAccount(accountId);
	}

	/**
	 * Delete account
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account) {
		accountDAO.deleteAccount(account);
	}

	/**
	 * Register an ebiller for an account
	 * 
	 * @param account
	 *            account that ebiller is to be registered with
	 * @param ebiller
	 *            ebiller to register
	 * @param registeredBy
	 *            accountOwner registering the ebiller with the account
	 * @param accountNumber
	 *            account number with ebiller for account
	 * @return eBillerRegistration representing the account-ebiller relationship
	 */
	public EBillerRegistration registerEBiller(Account account,
			EBiller ebiller, AccountOwner registeredBy, String accountNumber) {
		EBillerRegistration ebillerRegistration = new EBillerRegistration(
				account, ebiller, registeredBy, new Date(), accountNumber);
		accountDAO.registerEBiller(ebillerRegistration);
		return ebillerRegistration;
	}

	/**
	 * Unregister an ebiller from an account
	 * 
	 * @param ebillerRegistration
	 *            registration information to be removed
	 */
	public void unregisterEBiller(EBillerRegistration ebillerRegistration) {
		ebillerRegistration.getAccount().removeEBillerRegistration(
				ebillerRegistration);
		ebillerRegistration.getEbiller().removeEBillerRegistration(
				ebillerRegistration);
		accountDAO.unregisterEBiller(ebillerRegistration);
	}

	/**
	 * Get all accounts in system
	 */
	public List<Account> getAccounts() {
		return accountDAO.getAccounts();
	}

	/**
	 * Get all checking accounts in system
	 */
	public List<CheckingAccount> getCheckingAccounts() {
		return accountDAO.getCheckingAccounts();
	}

	/**
	 * Get all savings accounts in system
	 */
	public List<SavingsAccount> getSavingsAccounts() {
		return accountDAO.getSavingsAccounts();
	}

	/**
	 * Get all savings accounts in system
	 */
	public List<CdAccount> getCdAccounts() {
		return accountDAO.getCdAccounts();
	}

	/**
	 * Get all accounts that were created after minCreationDate and which have a
	 * balance greater than minBalance
	 * 
	 * @param minBalance
	 *            minBalance of accounts
	 * @param minCreationDate
	 *            minCreationDate of accounts
	 * @return accounts that were created after minCreationDate and which have a
	 *         balance greater than minBalance
	 */
	public List<Account> getAccountsUsingNameBasedParameters(long minBalance, Date minCreationDate) {
		return accountDAO.getAccountsUsingNameBasedParameters(minBalance, minCreationDate);
	}

	/**
	 * Get all accounts that were created after minCreationDate and which have a
	 * balance greater than minBalance
	 * 
	 * @param minBalance
	 *            minBalance of accounts
	 * @param minCreationDate
	 *            minCreationDate of accounts
	 * @return accounts that were created after minCreationDate and which have a
	 *         balance greater than minBalance
	 */
	public List<Account> getAccountsUsingGenericParameters(double minBalance,
			Date minCreationDate) {
		return accountDAO.getAccountsUsingGenericParameters(minBalance,
				minCreationDate);
	}

	/**
	 * Get all accounts in system and page the results
	 */
	public List<Account> getAccountsAndPage(int pageNumber, int resultsPerPage) {
		return accountDAO.getAccountsAndPage(pageNumber, resultsPerPage);
	}

	/**
	 * Get map of accountIds and balances - specifying columns in query example
	 * 
	 * @return map of accountIds and balances
	 */
	public Map<Long, Double> getAccountIdBalanceMap() {
		return accountDAO.getAccountIdBalanceMap();
	}

	/**
	 * Get min, max, and average account balance across all accounts
	 * 
	 * @return min, max, and average account balance across all accounts
	 */
	public Map<String, Double> getAccountBalanceStatistics() {
		return accountDAO.getAccountBalanceStatistics();
	}

	/**
	 * Get map of accountIds and average transaction amounts -- example of using
	 * group by
	 * 
	 * @return map of accountIds and average transaction amounts
	 */
	public Map<Long, Double> getAverageTransactionAmountByAccountId() {
		return accountDAO.getAverageTransactionAmountByAccountId();
	}

	/**
	 * Get map of accountIds and average transaction amounts for accounts with
	 * more than minTransaction transactions -- example of using group by and
	 * having
	 * 
	 * @return map of accountIds and average transaction amounts for accounts
	 *         with more than 20 transactions
	 */
	public Map<Long, Double> getAverageTransactionAmountByAccountId(
			long minTransactions) {
		return accountDAO
				.getAverageTransactionAmountByAccountId(minTransactions);
	}
}
