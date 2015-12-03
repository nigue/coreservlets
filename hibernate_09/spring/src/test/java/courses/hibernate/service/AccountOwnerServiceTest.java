package courses.hibernate.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import courses.hibernate.vo.AccountOwner;

/**
 * Service layer tests for AccountOwner
 */
public class AccountOwnerServiceTest extends ServiceTest {

	/**
	 * Test getAccountOwner by LastName And SSN Using HQL
	 */
	//@Test
	public void testGetAccountOwnerByLastNameAndSSNUsingHQL() {
		List<AccountOwner> accountOwners = accountOwnerService
				.getAccountOwnersWithHQL("MOUSE", "444-44-4444");
		TestCase.assertEquals(1, accountOwners.size());
		for (AccountOwner accountOwner : accountOwners) {
			TestCase.assertTrue(accountOwner.getLastName().equals("MOUSE"));
			TestCase.assertTrue(accountOwner.getSocialSecurityNumber().equals(
					"444-44-4444"));
		}
		System.out.println(accountOwners);
	}

}
