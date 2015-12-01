package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

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
	 * Get account owner by email address
	 * 
	 * @param email
	 *            email address to look up account owner by
	 * @return account owner
	 */
	public AccountOwner getAccountOwnerUsingRestrictions(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (AccountOwner) session.createCriteria(AccountOwner.class).add(
				Restrictions.eq("email", email)).uniqueResult();
	}

	/**
	 * Get account owner by email address
	 * 
	 * @param email
	 *            email address to look up account owner by
	 * @return account owner
	 */
	public AccountOwner getAccountOwnerUsingProperty(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (AccountOwner) session.createCriteria(AccountOwner.class).add(
				Property.forName("email").eq(email)).uniqueResult();
	}

	/**
	 * Get account owners by zipcode
	 * 
	 * @param email
	 *            email address to look up account owner by
	 * @return account owners with matching zipcode
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwners(String zip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session.createCriteria(AccountOwner.class)
				.add(Restrictions.eq("address.zipCode.zip", zip)).list();

	}

	/**
	 * Get account owners whose first name starts with firstNamePrefix and whose
	 * lastName starts with lastNamePrefix
	 * 
	 * @param firstNamePrefix
	 *            prefix of firstName
	 * @param lastNamePrefix
	 *            prefix of lastName
	 * @return account owners whose first names starts with firstNamePrefix and
	 *         whose lastName starts with lastNamePrefix
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwners(String firstNamePrefix,
			String lastNamePrefix) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		return (List<AccountOwner>) session.createCriteria(AccountOwner.class)
				.add(
						Restrictions.like("lastName", lastNamePrefix + "%")
								.ignoreCase()).add(
						Restrictions.like("firstName", firstNamePrefix + "%")
								.ignoreCase()).list();

	}

	/*
	 * Get account owners whose (first name starts with firstNamePrefix AND
	 * whose lastName starts with lastNamePrefix) OR (whose email ends with
	 * emailSuffix)
	 * 
	 * @param firstNamePrefix prefix of firstName
	 * 
	 * @param lastNamePrefix prefix of lastName
	 * 
	 * @param emailAddressSuffix suffix of emailAddress
	 * 
	 * @return account owners whose (first names starts with firstNamePrefix AND
	 * whose lastName starts with lastNamePrefix) OR (whose email ends with
	 * emailSuffix)
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwnersUsingOr(String firstNamePrefix,
			String lastNamePrefix, String emailAddressSuffix) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session
				.createCriteria(AccountOwner.class)
				.add(
						Restrictions.or(Restrictions.and(Restrictions.like(
								"lastName", lastNamePrefix + "%").ignoreCase(),
								Restrictions.like("firstName",
										firstNamePrefix + "%").ignoreCase()),
								Restrictions.like("email",
										"%" + emailAddressSuffix).ignoreCase()))
				.list();
	}

	/*
	 * Get account owners whose (first name starts with firstNamePrefix AND
	 * whose lastName starts with lastNamePrefix) OR (whose email ends with
	 * emailSuffix)
	 * 
	 * @param firstNamePrefix prefix of firstName
	 * 
	 * @param lastNamePrefix prefix of lastName
	 * 
	 * @param emailAddressSuffix suffix of emailAddress
	 * 
	 * @return account owners whose (first names starts with firstNamePrefix AND
	 * whose lastName starts with lastNamePrefix) OR (whose email ends with
	 * emailSuffix)
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwnersUsingJunctions(
			String firstNamePrefix, String lastNamePrefix,
			String emailAddressSuffix) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		return (List<AccountOwner>) session
				.createCriteria(AccountOwner.class)
				.add(
						Restrictions.disjunction().add(
								Restrictions.conjunction().add(
										Restrictions.like("lastName",
												lastNamePrefix + "%")
												.ignoreCase()).add(
										Restrictions.like("firstName",
												firstNamePrefix + "%")
												.ignoreCase())).add(
								Restrictions.like("email",
										"%" + emailAddressSuffix).ignoreCase()))
				.list();
	}

	/**
	 * Get account owners by a detached criteria
	 * 
	 * @param detachedCriteria
	 *            criteria used to filter account owners
	 * @return list of account owners that match provided criteria
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwners(DetachedCriteria detachedCriteria) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) detachedCriteria.getExecutableCriteria(
				session).list();
	}

	/**
	 * Get account owners which have accounts with a balance more than the
	 * accountBalanceMinimum
	 * 
	 * @param accountBalanceMinimum
	 *            minimum balance amount
	 * @return account owners which have accounts with a balance more than the
	 *         accountBalanceMinimum
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwners(long accountBalanceMinimum) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (List<AccountOwner>) session.createCriteria(AccountOwner.class).add(
				Restrictions.sqlRestriction(Long
						.toString(accountBalanceMinimum)
						+ " < all "
						+ " (SELECT A.BALANCE FROM ACCOUNT A, ACCOUNT_ACCOUNT_OWNER AAO"
						+ "  WHERE A.ACCOUNT_ID = AAO.ACCOUNT_ID AND"
						+ " AAO.ACCOUNT_OWNER_ID = {alias}.ACCOUNT_OWNER_ID)"))
				.list();
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
	 * Get account owners that are like the exampleAccountOwner and have the
	 * provided zipcode
	 * 
	 * @param accountOwner
	 *            example account owner used as criteria
	 * @return account owners that are like the exampleAccountOwner
	 */
	@SuppressWarnings("unchecked")
	public List<AccountOwner> getAccountOwners(AccountOwner accountOwner,
			String zip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Example exampleAccountOwner = Example.create(accountOwner).ignoreCase();
		return (List<AccountOwner>) session.createCriteria(AccountOwner.class)
				.add(exampleAccountOwner).add(
						Restrictions.eq("address.zipCode.zip", zip)).list();
	}
}
