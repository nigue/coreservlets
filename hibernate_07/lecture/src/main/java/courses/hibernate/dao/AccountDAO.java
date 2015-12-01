package courses.hibernate.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountEBillerSummary;
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

	@SuppressWarnings("unchecked")
	/*
	 * Get all accounts in system
	 */
	public List<Account> getAccounts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Account> accounts = (List<Account>) session.createCriteria(
				Account.class).addOrder(Order.asc("balance")).list();
		return accounts;
	}

	@SuppressWarnings("unchecked")
	/*
	 * Get all checking accounts in system
	 */
	public List<CheckingAccount> getCheckingAccounts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CheckingAccount> accounts = (List<CheckingAccount>) session
				.createCriteria(CheckingAccount.class).addOrder(
						Order.asc("balance")).list();
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
		List<CdAccount> accounts = (List<CdAccount>) session.createCriteria(
				CdAccount.class).addOrder(Order.asc("balance")).list();
		return accounts;
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
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts(double minBalance, double maxBalance) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<Account>) session.createCriteria(Account.class).add(
				Restrictions.between("balance", minBalance, maxBalance)).list();
	}

	/**
	 * Get accounts by accountIds
	 * 
	 * @param accountIds
	 *            identifiers of the accounts
	 * @return accounts with the accountIds
	 */
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts(Set<Long> accountIds) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<Account>) session.createCriteria(Account.class).add(
				Restrictions.in("accountId", accountIds)).list();
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
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts(long accountTransactionMaximum) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<Account>) session.createCriteria(Account.class).add(
				Restrictions.sqlRestriction(Long.toString(accountTransactionMaximum) + " > all "
						+ " (SELECT ATX.AMOUNT FROM ACCOUNT_TRANSACTION ATX"
						+ "  WHERE ATX.ACCOUNT_ID = {alias}.ACCOUNT_ID)"))
				.list();
	}

	/**
	 * Get a list of maps which contain accounts and transactions
	 * 
	 * @return list of maps which contain accounts and transactions
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, ?>> getAccountsAndTransactions() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<Map<String, ?>>) session.createCriteria(Account.class)
				.createAlias("accountTransactions", "atx").setResultTransformer(
						Criteria.ALIAS_TO_ENTITY_MAP).list();
	}

	/**
	 * Get a ebiller report containing the accountId, eBillerId, the number of
	 * ebillers for the account, and the balance for that ebiller
	 * 
	 * @return report containing the accountId, eBillerId, the number of
	 *         ebillers for the account, and the balance for that ebiller
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAccountEBillerReportAsObjectArray() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<Object[]>) session.createCriteria(Account.class)
				.createAlias("ebills", "e").setProjection(
						Projections.projectionList().add(
								Property.forName("accountId").group())
								.add(
										Property.forName("e.ebiller.ebillerId")
												.group()).add(
										Property.forName("e.ebiller.ebillerId")
												.count()).add(
										Property.forName("e.balance").avg()))
				.list();
	}

	/**
	 * Get a ebiller report containing the accountId, eBillerId, the number of
	 * ebillers for the account, and the balance for that ebiller
	 * 
	 * @return report containing the accountId, eBillerId, the number of
	 *         ebillers for the account, and the balance for that ebiller
	 */
	@SuppressWarnings("unchecked")
	public List<AccountEBillerSummary> getAccountEBillerReport() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountEBillerSummary>) session.createCriteria(
				Account.class).createAlias("ebills", "e").setProjection(
				Projections.projectionList().add(
						Property.forName("accountId").group().as("accountId"))
						.add(
								Property.forName("e.ebiller.ebillerId").group()
										.as("ebillerId")).add(
								Property.forName("e.ebiller.ebillerId").count()
										.as("count")).add(
								Property.forName("e.balance").avg().as(
										"averageBalance")))
				.setResultTransformer(
						new AliasToBeanResultTransformer(
								AccountEBillerSummary.class)).list();
	}

}
