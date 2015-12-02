package courses.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountOwner;

/**
 * Data Access Object for Account Owner
 */
public class AccountOwnerDAO {

	/**
	 * Create a new account owner or update an existing one
	 * 
	 * @param accountOwner
	 *            account owner to be persisted
	 */
	public void saveOrUpdateAccountOwner(AccountOwner accountOwner) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(accountOwner);
	}

	/**
	 * Retrieve an account owner from the data store
	 * 
	 * @param accountOwnerId
	 *            identifier of the account owner to be retrieved
	 * @return accountOwner represented by the identifier provided
	 */
	public AccountOwner getAccountOwner(long accountOwnerId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		AccountOwner accountOwner = (AccountOwner) session.get(
				AccountOwner.class, accountOwnerId);
		return accountOwner;
	}

	/**
	 * Delete account owner from data store
	 * 
	 * @param accountOwner
	 *            accountOwner to be deleted
	 */
	public void deleteAccountOwner(AccountOwner accountOwner) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(accountOwner);
	}

	/**
	 * Get account owners that are like the exampleAccountOwner
	 * 
	 * @param accountOwner
	 *            example account owner used as criteria
	 * @return account owners that are like the exampleAccountOwner
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwners(AccountOwner accountOwner) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Example exampleAccountOwner = Example.create(accountOwner).ignoreCase();
		return (List<AccountOwner>) session.createCriteria(AccountOwner.class)
				.add(exampleAccountOwner).list();
	}

	/**
	 * Get all account owners - global named query example
	 * 
	 * @return all account owners in system
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwnersUsingGlobalQuery() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session
				.getNamedQuery("getAllAccountOwners").list();
	}

	/**
	 * Get all account owners - class query example
	 * 
	 * @return all account owners in system in a given zipcode
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwnersUsingClassQuery(String zip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session.getNamedQuery(
				"courses.hibernate.vo.AccountOwner.getAccountOwnersByZip")
				.setParameter("zip", zip).list();
	}

	/**
	 * Return account owners with upper case last names and lower case first
	 * names -- example of using database functions
	 * 
	 * @return account owners with upper case last names and lower case first
	 *         names
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAccountOwnersWithFormattedNames() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Object[]> names = (List<Object[]>) session.createQuery(
				"select upper(lastName),"
						+ "lower(firstName) from AccountOwner").list();

		List<String> nameList = new ArrayList<String>();
		for (Object[] name : names) {
			String lastName = (String) name[0];
			String firstName = (String) name[1];
			nameList.add(lastName + " " + firstName);
		}
		return nameList;
	}

	/**
	 * Get account owners whose work phones match the ebiller phone numbers with
	 * the specified ebiller name
	 * 
	 * @param ebillerName
	 *            name of ebiller
	 * @return account owners whose work phones match the ebiller phone numbers
	 *         with the specified ebiller name
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwnersUsingThetaJoin(String ebillerName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session
				.createQuery(
						"select owner from AccountOwner owner, EBiller ebiller"
								+ " where owner.cellPhone = ebiller.phone and ebiller.name = '"
								+ ebillerName + "'").list();
	}

	/**
	 * Get count of account owners in the system
	 * 
	 * @return count of accountOwners
	 */
	public long getCountOfAccountOwner() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Long) session.createQuery(
				"select count(ao) from AccountOwner ao").uniqueResult();
	}

}
