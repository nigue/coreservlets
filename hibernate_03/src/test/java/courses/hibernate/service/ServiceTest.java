package courses.hibernate.service;

import java.util.Date;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Parent class for Service Tests that contains helper methods
 */
public abstract class ServiceTest {

	/**
	 * Create an ebiller
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
	 * @param ebiller ebiller to be deleted
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
	 * @param ebiller ebiller associated with ebill
	 * @return ebill
	 */
	protected EBill buildEBill(EBiller ebiller) {
		long dueDate = new Date().getTime() + 30 * 24 * 60 * 60 * 1000;
		EBill ebill = new EBill(new Date(), new Date(dueDate), ebiller, 25,
				1000);
		return ebill;
	}

	/**
	 * Create an ebill object
	 * @return ebill that was created
	 */
	protected EBill createEBill() {
		EBill ebill = buildEBill(createEBiller());
		EBillService ebillService = new EBillService();
		ebillService.saveOrUpdateEBill(ebill);
		return ebill;
	}

	/**
	 * Delete an ebill
	 * @param ebill ebill to be deleted
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
	 * Build an account
	 * @return account
	 */
	protected Account buildAccount() {
		Account account = new Account();
		account.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		return account;
	}

	/**
	 * Create an account
	 * @return account that was created
	 */
	protected Account createAccount(){
		AccountService accountService = new AccountService();
		Account account = buildAccount();
		accountService.saveOrUpdateAccount(account);
		return account;
	}
	
	/**
	 * Delete an account
	 * @param account account to be deleted
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
	 * Create an account transaction
	 * @param amount amount for transaction
	 * @return created account transaction
	 */
	protected AccountTransaction createAccountTransaction(double amount){
		AccountTransaction accountTransaction = new AccountTransaction();
		accountTransaction.setAmount(amount);
		accountTransaction.setTransactionDate(new Date());
		accountTransaction
				.setTransactionType(AccountTransaction.TRANSACTION_TYPE_DEBIT);
		AccountTransactionService accountTransactionService = new AccountTransactionService();
		accountTransactionService
				.saveOrUpdateAccountTransaction(accountTransaction);
		return accountTransaction;		
	}
	
	/**
	 * Delete an account transaction
	 * @param accountTransaction accountTransaction to be deleted
	 */
	protected void deleteAccountTransaction(AccountTransaction accountTransaction){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(accountTransaction);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();		
	}
}
