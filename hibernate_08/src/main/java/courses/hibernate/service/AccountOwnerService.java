package courses.hibernate.service;

import java.util.List;

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
	 * Get all account owners - global named query example
	 * 
	 * @return all account owners in system
	 */
	public List<AccountOwner> getAccountOwnersUsingGlobalQuery() {
		return accountOwnerDAO.getAccountOwnersUsingGlobalQuery();
	}

	/**
	 * Get all account owners - class query example
	 * 
	 * @return all account owners in system in a given zipcode
	 */
	public List<AccountOwner> getAccountOwnersUsingClassQuery(String zip) {
		return accountOwnerDAO.getAccountOwnersUsingClassQuery(zip);
	}

	/**
	 * Return account owners with upper case last names and lower case first
	 * names -- example of using database functions
	 * 
	 * @return
	 */
	public List<String> getAccountOwnersWithFormattedNames() {
		return accountOwnerDAO.getAccountOwnersWithFormattedNames();
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
	public List<AccountOwner> getAccountOwnersUsingThetaJoin(String ebillerName) {
		return accountOwnerDAO.getAccountOwnersUsingThetaJoin(ebillerName);
	}

	/**
	 * Get count of account owners in the system
	 * 
	 * @return count of accountOwners
	 */
	public long getCountOfAccountOwner() {
		return accountOwnerDAO.getCountOfAccountOwner();
	}

}
