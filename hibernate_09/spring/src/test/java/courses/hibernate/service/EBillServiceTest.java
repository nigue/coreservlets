package courses.hibernate.service;

import junit.framework.TestCase;

import org.junit.Test;

import courses.hibernate.vo.Account;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Service layer tests for ebills
 */
public class EBillServiceTest extends ServiceTest {

	/**
	 * Test creating of ebill using stored procedures
	 */
	//@Test
	public void testCreateEBill() {

		Account account = createCheckingAccount();
		EBiller ebiller = createEBiller();
		EBill ebill = buildEBill(ebiller, account);

		ebillService.saveOrUpdateEBill(ebill);

		System.out.println("var ebill = " + ebill);

		// Confirm that creation date was set
		// ------- ---- -------- ---- --- ---
		TestCase.assertNotNull(ebill.getCreationDate());

		deleteAccount(account);
	}

	/**
	 * Test update of ebill and retrieval of updateDate after it's set by
	 * trigger
	 */
	//@Test
	public void testPayEBill() {

		Account account = createCheckingAccount();
		EBiller ebiller = createEBiller();
		EBill ebill = buildEBill(ebiller, account);

		ebillService.saveOrUpdateEBill(ebill);

		// Update date should be null
		// ------ ---- ------ -- ----
		TestCase.assertNull(ebill.getUpdateDate());

		// Update ebill by setting transaction on it
		// ------ ----- -- ------- ----------- -- --
		ebill.setAccountTransaction(createElectronicAccountTransaction(account,
				10L));
		ebillService.saveOrUpdateEBill(ebill);

		System.out.println("var ebill = " + ebill);

		// Confirm that update date was set
		// ------- ---- ------ ---- --- ---
		TestCase.assertNotNull(ebill.getUpdateDate());

		deleteAccount(account);
		deleteEBiller(ebiller);
	}

	/**
	 * Test payment of ebill by setting a new account transaction and have the
	 * account transaction be created as part of the cascade.
	 */
	//@Test
	public void testPayBillWithCascade() {

		// Create EBill and build an Account Transaction and set it on
		// eBill. Don't explicitly create Account Transaction -- it is
		// automatically created when EBill is updated
		// ------------------------------------------------------------
		Account account = createCheckingAccount();
		EBill ebill = createEBill(account);
		AccountTransaction accountTransaction = buildElectronicAccountTransaction(
				account, ebill.getBalance());

		ebill.setAccountTransaction(accountTransaction);
		ebillService.saveOrUpdateEBill(ebill);

		// Validate that transaction has been persisted on ebill
		// -------- ---- ----------- --- ---- --------- -- -----

		ebill = ebillService.getEBill(ebill.getEbillId());
		TestCase.assertNotNull(ebill.getAccountTransaction());

		// cleanup
		// -------
		deleteAccount(account);
	}

}
