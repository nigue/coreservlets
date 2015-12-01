package courses.hibernate.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import courses.hibernate.dao.AccountOwnerDAO;
import courses.hibernate.vo.AccountOwner;

/**
 * Service layer for Account Owner
 */
public class AccountOwnerService {
	AccountOwnerDAO accountOwnerDAO = new AccountOwnerDAO();

	/**
	 * Create a new account owner or update an existing one
	 * 
	 * @param accountOwner
	 *            account owner to be persisted
	 */
	public void saveOrUpdateAccountOwner(AccountOwner accountOwner) {
		accountOwnerDAO.saveOrUpdateAccountOwner(accountOwner);
	}

	/**
	 * Retrieve an account owner
	 * 
	 * @param accountOwnerId
	 *            identifier of the account owner to be retrieved
	 * @return accountOwner represented by the identifier provided
	 */
	public AccountOwner getAccountOwner(long accountOwnerId) {
		return accountOwnerDAO.getAccountOwner(accountOwnerId);
	}

	/**
	 * Delete account owner
	 * 
	 * @param accountOwner
	 *            accountOwner to be deleted
	 */
	public void deleteAccountOwner(AccountOwner accountOwner) {
		accountOwnerDAO.deleteAccountOwner(accountOwner);
	}

	/**
	 * Get account owner by email address
	 * 
	 * @param email
	 *            email address to look up account owner by
	 * @return account owner
	 */
	public AccountOwner getAccountOwnerUsingRestrictions(String email) {
		return accountOwnerDAO.getAccountOwnerUsingRestrictions(email);
	}

	/**
	 * Get account owner by email address
	 * 
	 * @param email
	 *            email address to look up account owner by
	 * @return account owner
	 */
	public AccountOwner getAccountOwnerUsingProperty(String email) {
		return accountOwnerDAO.getAccountOwnerUsingProperty(email);
	}

	/**
	 * Get account owners by zipcode
	 * 
	 * @param email
	 *            email address to look up account owner by
	 * @return account owners with matching zipcode
	 */
	public List<AccountOwner> getAccountOwners(String zip) {
		return accountOwnerDAO.getAccountOwners(zip);
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
	public List<AccountOwner> getAccountOwners(String firstNamePrefix,
			String lastNamePrefix) {
		return accountOwnerDAO
				.getAccountOwners(firstNamePrefix, lastNamePrefix);
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
	public List<AccountOwner> getAccountOwnersUsingOr(String firstNamePrefix,
			String lastNamePrefix, String emailAddressSuffix) {
		return accountOwnerDAO.getAccountOwnersUsingOr(firstNamePrefix,
				lastNamePrefix, emailAddressSuffix);
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
	public List<AccountOwner> getAccountOwnersUsingJunctions(
			String firstNamePrefix, String lastNamePrefix,
			String emailAddressSuffix) {
		return accountOwnerDAO.getAccountOwnersUsingJunctions(firstNamePrefix,
				lastNamePrefix, emailAddressSuffix);
	}

	/**
	 * Get account owners by a detached criteria
	 * 
	 * @param detachedCriteria
	 *            criteria used to filter account owners
	 * @return list of account owners that match provided criteria
	 */
	public List<AccountOwner> getAccountOwners(DetachedCriteria detachedCriteria) {
		return accountOwnerDAO.getAccountOwners(detachedCriteria);
	}

	/**
	 * Get account owners that are like the exampleAccountOwner
	 * 
	 * @param accountOwner
	 *            example account owner used as criteria
	 * @return account owners that are like the exampleAccountOwner
	 */
	public List<AccountOwner> getAccountOwners(AccountOwner accountOwner) {
		return accountOwnerDAO.getAccountOwners(accountOwner);
	}

	/**
	 * Get account owners that are like the exampleAccountOwner and have the
	 * provided zipcode
	 * 
	 * @param accountOwner
	 *            example account owner used as criteria
	 * @return account owners that are like the exampleAccountOwner
	 */
	public List<AccountOwner> getAccountOwners(AccountOwner accountOwner,
			String zip) {
		return accountOwnerDAO.getAccountOwners(accountOwner, zip);
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
	public List<AccountOwner> getAccountOwners(long accountBalanceMinimum) {
		return accountOwnerDAO.getAccountOwners(accountBalanceMinimum);
	}

}
