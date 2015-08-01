package courses.hibernate.service;

import java.util.Date;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.Address;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;
import courses.hibernate.vo.SavingsAccount;
import courses.hibernate.vo.ZipCode;

/**
 * Parent class for Service Tests that contains helper methods
 */
public abstract class ServiceTest {

	/**
	 * Create an ebiller
	 * 
	 * @return ebiller that was created
	 */
	protected EBiller createEBiller() {
		EBiller eBiller = new EBiller();
		eBiller.setName("DISCOVER CARD");
		eBiller.setBillingAddress("500 Madison Avenue NY, NY 10015");
		eBiller.setPhone("1-800-DISCOVER");
		EBillerService ebillerService = new EBillerService();
		ebillerService.saveOrUpdateEBiller(eBiller);
		return eBiller;
	}

	/**
	 * Delete an ebiller
	 * 
	 * @param ebiller
	 *            ebiller to be deleted
	 */
	protected void deleteEBiller(EBiller ebiller) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EBillerService ebillerService = new EBillerService();
		ebillerService.deleteEBiller(ebiller);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	/**
	 * Build an ebill object
	 * 
	 * @param ebiller
	 *            ebiller associated with ebill
	 * @return ebill
	 */
	protected EBill buildEBill(EBiller ebiller, Account account) {
		long dueDate = new Date().getTime() + 30 * 24 * 60 * 60 * 1000;
		EBill ebill = new EBill(new Date(), new Date(dueDate), ebiller,
				account, 25, 1000);
		return ebill;
	}

	/**
	 * Create an ebill object
	 * 
	 * @return ebill that was created
	 */
	protected EBill createEBill(Account account) {
		EBill ebill = buildEBill(createEBiller(), account);
		EBillService ebillService = new EBillService();
		ebillService.saveOrUpdateEBill(ebill);
		return ebill;
	}

	/**
	 * Delete an ebill
	 * 
	 * @param ebill
	 *            ebill to be deleted
	 */
	protected void deleteEBill(EBill ebill) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EBillService ebillService = new EBillService();
		ebillService.deleteEBill(ebill);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	/**
	 * Build a savings account
	 * 
	 * @return savingsAccount
	 */
	protected SavingsAccount buildSavingsAccount() {
		SavingsAccount account = new SavingsAccount();
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		account.setInterestRate(3.5);
		return account;
	}

	/**
	 * Build a checking account
	 * 
	 * @return checkingAccount
	 */
	protected CheckingAccount buildCheckingAccount() {
		CheckingAccount account = new CheckingAccount();
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		account.setCheckStyle("ELEPHANTS");
		return account;
	}

	/**
	 * Create an account
	 * 
	 * @return account that was created
	 */
	protected SavingsAccount createSavingsAccount() {
		return (SavingsAccount) createAccount(buildSavingsAccount());
	}

	/**
	 * Create an account
	 * 
	 * @return account that was created
	 */
	protected CheckingAccount createCheckingAccount() {
		return (CheckingAccount) createAccount(buildCheckingAccount());
	}

	/**
	 * Create an account
	 * 
	 * @return account that was created
	 */
	protected Account createAccount(Account account) {
		AccountService accountService = new AccountService();
		accountService.saveOrUpdateAccount(account);
		return account;
	}

	/**
	 * Delete an account
	 * 
	 * @param account
	 *            account to be deleted
	 */
	protected void deleteAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AccountService accountService = new AccountService();
		accountService.deleteAccount(account);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	/**
	 * Build an account transaction
	 * 
	 * @param amount
	 *            amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction buildAccountTransaction(Account account, double amount) {
		AccountTransaction accountTransaction = new AccountTransaction();
		accountTransaction.setAmount(amount);
		accountTransaction.setTransactionDate(new Date());
		accountTransaction
				.setTransactionType(AccountTransaction.TRANSACTION_TYPE_DEBIT);
		accountTransaction.setAccount(account);
		return accountTransaction;
	}
	
	/**
	 * Create an account transaction
	 * 
	 * @param amount
	 *            amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction createAccountTransaction(Account account,double amount) {
		AccountTransaction accountTransaction = buildAccountTransaction(account, amount);
		AccountTransactionService accountTransactionService = new AccountTransactionService();
		accountTransactionService
				.saveOrUpdateAccountTransaction(accountTransaction);
		return accountTransaction;
	}

	/**
	 * Delete an account transaction
	 * 
	 * @param accountTransaction
	 *            accountTransaction to be deleted
	 */
	protected void deleteAccountTransaction(
			AccountTransaction accountTransaction) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(accountTransaction);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	/**
	 * Build an account owner
	 * 
	 * @return account owner
	 */
	protected AccountOwner buildAccountOwner(String socialSecurityNumber) {
		AccountOwner accountOwner = new AccountOwner();
		accountOwner.setCellPhone("111-111-1111");
		accountOwner.setFirstName("Matthew");
		accountOwner.setLastName("Cherry");
		accountOwner.setHomePhone("222-222-2222");
		accountOwner.setSocialSecurityNumber(socialSecurityNumber);
		Address address = new Address();
		address.setStreetAddress("123 Fourth Street");
		address.setCity("Gaithersburg");
		address.setState("Maryland");
		ZipCode zipCode = new ZipCode();
		zipCode.setZip("11111");
		zipCode.setPlus4("1111");
		address.setZipCode(zipCode);
		accountOwner.setAddress(address);
		return accountOwner;
	}

	/**
	 * Create an account owner
	 * 
	 * @return account owner that was created
	 */
	protected AccountOwner createAccountOwner(String socialSecurityNumber) {
		AccountOwnerService accountOwnerService = new AccountOwnerService();
		AccountOwner accountOwner = buildAccountOwner(socialSecurityNumber);
		accountOwnerService.saveOrUpdateAccountOwner(accountOwner);
		return accountOwner;
	}

	/**
	 * Delete an account owner
	 * 
	 * @param accountOwner
	 *            accountOwner to be deleted
	 */
	protected void deleteAccountOwner(AccountOwner accountOwner) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AccountOwnerService accountOwnerService = new AccountOwnerService();
		accountOwnerService.deleteAccountOwner(accountOwner);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}
}
