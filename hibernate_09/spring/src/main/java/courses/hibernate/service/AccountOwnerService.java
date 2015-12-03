package courses.hibernate.service;

import java.util.List;

import courses.hibernate.dao.AccountOwnerDAO;
import courses.hibernate.vo.AccountOwner;

/**
 * Service layer for Account Owner
 */
public class AccountOwnerService {
	AccountOwnerDAO accountOwnerDAO;

	/**
	 * Set accountOwnerDAO
	 * 
	 * @param accountOwnerDAO
	 *            accountOwnerDAO to set. Used by Spring.
	 */
	public void setAccountOwnerDAO(AccountOwnerDAO accountOwnerDAO) {
		this.accountOwnerDAO = accountOwnerDAO;
	}

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
	 * Retrieve an account owner from the data store by lastName and SSN using
	 * HQL
	 * 
	 * @param lastName
	 *            last name of account owner
	 * @param socialSecurityNumber
	 *            ssn of account owner
	 * @return accountOwner represented by last name and social security number
	 */
	public List<AccountOwner> getAccountOwnersWithHQL(String lastName,
			String socialSecurityNumber) {
		return accountOwnerDAO.getAccountOwnersWithHQL(lastName,
				socialSecurityNumber);
	}
}
