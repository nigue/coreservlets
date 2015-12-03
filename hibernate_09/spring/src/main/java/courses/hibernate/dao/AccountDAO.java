package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import courses.hibernate.vo.Account;
import courses.hibernate.vo.CdAccount;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBillerRegistration;
import courses.hibernate.vo.SavingsAccount;

/**
 * Data Access Object for Account
 */
public class AccountDAO {

	private SessionFactory sessionFactory;

	/**
	 * Constructor to create AccountDAO. Used by Spring
	 * 
	 * @param sessionFactory
	 *            Hibernate session factory
	 */
	public AccountDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account
	 *            account to be persisted
	 */
	public void saveOrUpdateAccount(Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(account);
	}

	/**
	 * Retrieve an account from the data store
	 * 
	 * @param accountId
	 *            identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		Session session = sessionFactory.getCurrentSession();
		Account account = (Account) session.get(Account.class, accountId);
		return account;
	}

	/**
	 * Delete account from data store
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(account);
	}

	/**
	 * Save an ebillerRegistraton (ebiller-account relationship)
	 * 
	 * @param ebillerRegistration
	 *            ebillerRegistration to be saved
	 */
	public void registerEBiller(EBillerRegistration ebillerRegistration) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ebillerRegistration);
	}

	/**
	 * Delete an ebillerRegistration (ebiller-account relationship)
	 * 
	 * @param ebillerRegistration
	 *            ebillerRegistration to be deleted
	 */
	public void unregisterEBiller(EBillerRegistration ebillerRegistration) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(ebillerRegistration);
	}

	/*
	 * Get all accounts in system
	 */
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Account>) session.createQuery(
				"from Account order by balance asc").list();
	}

	/*
	 * Get all checking accounts in system
	 */
	@SuppressWarnings("unchecked")
	public List<CheckingAccount> getCheckingAccounts() {
		Session session = sessionFactory.getCurrentSession();
		return (List<CheckingAccount>) session.createQuery(
				"from CheckingAccount order by balance asc").list();
	}

	@SuppressWarnings("unchecked")
	/*
	 * Get all savings accounts in system
	 */
	public List<SavingsAccount> getSavingsAccounts() {
		Session session = sessionFactory.getCurrentSession();
		return (List<SavingsAccount>) session.createQuery(
				"from SavingsAccount order by balance asc").list();
	}

	/**
	 * Get all CD accounts in system
	 */
	@SuppressWarnings("unchecked")
	public List<CdAccount> getCdAccounts() {
		Session session = sessionFactory.getCurrentSession();
		return (List<CdAccount>) session.createQuery(
				"from CdAccount order by balance asc").list();
	}

	/**
	 * Use batch update to add interest to savings accounts
	 * 
	 * @return number of savings accounts updated
	 */
	public int addInterestToSavingsAccounts() {
		Session session = sessionFactory.getCurrentSession();

		Query q = session.createQuery("update versioned Account set balance="
				+ " (balance + (balance*interestRate))"
				+ " where account_type='SAVINGS' ");

		// return number of objects updated
		// ------ ------ -- ------- -------
		return q.executeUpdate();
	}

	/**
	 * Archive all accounts
	 * 
	 * @return number of accounts archived
	 */
	public int archiveAccounts() {
		Session session = sessionFactory.getCurrentSession();

		Query q = session.createQuery("insert into ArchivedAccount(accountId, "
				+ " creationDate, balance) select a.accountId, "
				+ " a.creationDate, a.balance from Account a");

		// return number of objects inserted
		// ------ ------ -- ------- --------
		return q.executeUpdate();
	}

	/**
	 * Delete all archived accounts
	 * 
	 * @return number of accounts deleted
	 */
	public int deleteArchivedAccounts() {
		Session session = sessionFactory.getCurrentSession();

		Query q = session.createQuery("delete from ArchivedAccount");

		// return number of objects deleted
		// ------ ------ -- ------- --------
		return q.executeUpdate();
	}
}
