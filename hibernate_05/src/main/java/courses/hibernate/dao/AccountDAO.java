package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.CdAccount;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBillerRegistration;
import courses.hibernate.vo.SavingsAccount;

/**
 * Data Access Object for Account
 */
public class AccountDAO {

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account
	 *            account to be persisted
	 */
	public void saveOrUpdateAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Account account = (Account) session.get(Account.class, accountId);
		return account;
	}

	@SuppressWarnings("unchecked")
	/*
	 * Get all accounts in system
	 */
	public List<Account> getAccounts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Account> accounts = (List<Account>) session.createCriteria(
				Account.class).list();
		return accounts;
	}

	@SuppressWarnings("unchecked")
	/*
	 * Get all checking accounts in system
	 */
	public List<CheckingAccount> getCheckingAccounts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CheckingAccount> accounts = (List<CheckingAccount>) session
				.createCriteria(CheckingAccount.class).list();
		return accounts;
	}

	@SuppressWarnings("unchecked")
	/*
	 * Get all savings accounts in system
	 */
	public List<SavingsAccount> getSavingsAccounts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<SavingsAccount> accounts = (List<SavingsAccount>) session
				.createCriteria(SavingsAccount.class).list();
		return accounts;
	}

	@SuppressWarnings("unchecked")
	/*
	 * Get all CD accounts in system
	 */
	public List<CdAccount> getCdAccounts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CdAccount> accounts = (List<CdAccount>) session
				.createCriteria(CdAccount.class).list();
		return accounts;
	}

	/**
	 * Delete account from data store
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(account);
	}

	/**
	 * Save an ebillerRegistraton (ebiller-account relationship)
	 * 
	 * @param ebillerRegistration
	 *            ebillerRegistration to be saved
	 */
	public void registerEBiller(EBillerRegistration ebillerRegistration) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(ebillerRegistration);
	}

	/**
	 * Delete an ebillerRegistration (ebiller-account relationship)
	 * 
	 * @param ebillerRegistration
	 *            ebillerRegistration to be deleted
	 */
	public void unregisterEBiller(EBillerRegistration ebillerRegistration) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(ebillerRegistration);
	}
}
