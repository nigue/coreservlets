package courses.hibernate.service;

import java.util.Date;

import courses.hibernate.util.SpringUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountOwner;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.Address;
import courses.hibernate.vo.CdAccount;
import courses.hibernate.vo.CheckingAccount;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;
import courses.hibernate.vo.ElectronicAccountTransaction;
import courses.hibernate.vo.PhysicalAccountTransaction;
import courses.hibernate.vo.SavingsAccount;
import courses.hibernate.vo.ZipCode;

/**
 * Parent class for Service Tests that contains helper methods
 */
public abstract class ServiceTest {

	AccountService accountService = SpringUtil.getAccountService();
	AccountTransactionService accountTransactionService = SpringUtil
			.getAccountTransactionService();
	AccountOwnerService accountOwnerService = SpringUtil
			.getAccountOwnerService();
	EBillService ebillService = SpringUtil.getEBillService();
	EBillerService ebillerService = SpringUtil.getEBillerService();

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
		ebillerService.deleteEBiller(ebiller);
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
		ebillService.deleteEBill(ebill);
	}

	/**
	 * Build a CD account
	 * 
	 * @return cdAccount
	 */
	protected CdAccount buildCdAccount() {
		CdAccount account = new CdAccount();
		account.setBalance(1000L);
		account.setInterestRate(3.5);
		account.setMaturityDate(new Date());
		return account;
	}

	/**
	 * Build a savings account
	 * 
	 * @return savingsAccount
	 */
	protected SavingsAccount buildSavingsAccount() {
		SavingsAccount account = new SavingsAccount();
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
		account.setBalance(1000L);
		account.setCheckStyle("ELEPHANTS");
		return account;
	}

	/**
	 * Create a savings account
	 * 
	 * @return savings account that was created
	 */
	protected SavingsAccount createSavingsAccount() {
		return (SavingsAccount) createAccount(buildSavingsAccount());
	}

	/**
	 * Create a checking account
	 * 
	 * @return checking account that was created
	 */
	protected CheckingAccount createCheckingAccount() {
		return (CheckingAccount) createAccount(buildCheckingAccount());
	}

	/**
	 * Create a cd account
	 * 
	 * @return cd account that was created
	 */
	protected CdAccount createCdAccount() {
		return (CdAccount) createAccount(buildCdAccount());
	}

	/**
	 * Create an account
	 * 
	 * @return account that was created
	 */
	protected Account createAccount(Account account) {
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
		accountService.deleteAccount(account);
	}

	/**
	 * Build an electronic account transaction
	 * 
	 * @param amount
	 *            amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction buildElectronicAccountTransaction(
			Account account, double amount) {
		ElectronicAccountTransaction accountTransaction = new ElectronicAccountTransaction();
		accountTransaction.setTransactionDate(new Date());
		accountTransaction.setAmount(amount);
		accountTransaction
				.setTransactionType(AccountTransaction.TRANSACTION_TYPE_DEBIT);
		accountTransaction.setAccount(account);
		accountTransaction.setIpAddress("1.1.1.1");
		return accountTransaction;
	}

	/**
	 * Create an electronic account transaction
	 * 
	 * @param amount
	 *            amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction createElectronicAccountTransaction(
			Account account, double amount) {
		AccountTransaction accountTransaction = buildElectronicAccountTransaction(
				account, amount);
		createAccountTransaction(accountTransaction);
		return accountTransaction;
	}

	/**
	 * Build a physical account transaction
	 * 
	 * @param amount
	 *            amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction buildPhysicalAccountTransaction(
			Account account, double amount) {
		PhysicalAccountTransaction accountTransaction = new PhysicalAccountTransaction();
		accountTransaction.setAmount(amount);
		accountTransaction
				.setTransactionType(AccountTransaction.TRANSACTION_TYPE_DEBIT);
		accountTransaction.setAccount(account);
		accountTransaction.setTeller("John Doe");
		accountTransaction.setBankName("Chevy Chase Bank");
		return accountTransaction;
	}

	/**
	 * Create an physical account transaction
	 * 
	 * @param amount
	 *            amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction createPhysicalAccountTransaction(
			Account account, double amount) {
		AccountTransaction accountTransaction = buildPhysicalAccountTransaction(
				account, amount);
		createAccountTransaction(accountTransaction);
		return accountTransaction;
	}

	/**
	 * Create an account transaction
	 * 
	 * @param accountTransaction
	 *            accountTransaction to create
	 * @return created account transaction
	 */
	protected AccountTransaction createAccountTransaction(
			AccountTransaction accountTransaction) {
		accountTransactionService
				.saveOrUpdateAccountTransaction(accountTransaction);
		return accountTransaction;
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
		accountOwner.setEmail(socialSecurityNumber);
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
		accountOwnerService.deleteAccountOwner(accountOwner);
	}

	/**
	 * Sleep for some time
	 * 
	 * @param milliseconds
	 *            time in ms to sleep
	 */
	protected void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
