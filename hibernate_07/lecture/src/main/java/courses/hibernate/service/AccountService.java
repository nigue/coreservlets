package courses.hibernate.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import courses.hibernate.dao.AccountDAO;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountEBillerSummary;
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
	 * Get accounts in a range of balance -- accounts where balance is greater
	 * than or equal to minBalance and less than or equal to maxBalance.
	 * 
	 * @param minBalance
	 *            minimum balance
	 * @param maxBalance
	 *            maximum balance
	 * @return accounts where balance is greater than or equal to minBalance and
	 *         less than or equal to maxBalance.
	 */
	public List<Account> getAccounts(double minBalance, double maxBalance) {
		return accountDAO.getAccounts(minBalance, maxBalance);
	}

	/**
	 * Get accounts by accountIds
	 * 
	 * @param accountIds
	 *            identifiers of the accounts
	 * @return accounts with the accountIds
	 */
	public List<Account> getAccounts(Set<Long> accountIds) {
		return accountDAO.getAccounts(accountIds);
	}

	/**
	 * Get accounts which have transactions that are less than the
	 * accountTransactionMaximum
	 * 
	 * @param accountTransactionMaximum
	 *            maximum transaction amount
	 * @return accounts which have transactions that are less than the
	 *         accountTransactionMaximum
	 */
	public List<Account> getAccounts(long accountTransactionMaximum) {
		return accountDAO.getAccounts(accountTransactionMaximum);
	}

	/**
	 * Get a list of maps which contain accounts and transactions
	 * 
	 * @return list of maps which contain accounts and transactions
	 */
	public List<Map<String, ?>> getAccountsAndTransactions() {
		return accountDAO.getAccountsAndTransactions();
	}

	/**
	 * Get a ebiller report containing the accountId, eBillerId, the number of
	 * ebillers for the account, and the balance for that ebiller
	 * 
	 * @return report containing the accountId, eBillerId, the number of
	 * ebillers for the account, and the balance for that ebiller
	 */
	public List<Object[]> getAccountEBillerReportAsObjectArray() {
		return accountDAO.getAccountEBillerReportAsObjectArray();
	}

	/**
	 * Get a ebiller report containing the accountId, eBillerId, the number of
	 * ebillers for the account, and the balance for that ebiller
	 * 
	 * @return report containing the accountId, eBillerId, the number of
	 *         ebillers for the account, and the balance for that ebiller
	 */
	public List<AccountEBillerSummary> getAccountEBillerReport() {
		return accountDAO.getAccountEBillerReport();
	}
}
