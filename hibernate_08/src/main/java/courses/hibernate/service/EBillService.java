package courses.hibernate.service;

import java.util.List;
import java.util.Map;

import courses.hibernate.dao.EBillDAO;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;

/**
 * Service layer for EBill
 */
public class EBillService {
	EBillDAO ebillDAO = new EBillDAO();

	/**
	 * Create a new ebill or update an existing one
	 * 
	 * @param ebill
	 *            ebill to be persisted
	 */
	public void saveOrUpdateEBill(EBill ebill) {
		ebillDAO.saveOrUpdateEBill(ebill);
	}

	/**
	 * Retrieve an ebill
	 * 
	 * @param ebillId
	 *            identifier of the ebill to be retrieved
	 * @return ebill represented by the identifier provided
	 */
	public EBill getEBill(long ebillId) {
		return ebillDAO.getEBill(ebillId);
	}

	/**
	 * Delete ebill from data store
	 * 
	 * @param ebill
	 *            ebill to be deleted
	 */
	public void deleteEBill(EBill ebill) {
		ebillDAO.deleteEBill(ebill);
	}

	/**
	 * Merge ebill with data store
	 * 
	 * @param ebill
	 *            ebill to be merge
	 */
	public void mergeEBill(EBill ebill) {
		ebillDAO.mergeEBill(ebill);
	}

	/**
	 * Reattach ebill with lock
	 * 
	 * @param ebill
	 *            ebill to be reattached
	 */
	public void reattachEBillWithLock(EBill ebill) {
		ebillDAO.reattachEBillWithLock(ebill);
	}

	/**
	 * Get eBills and eBillers by eBillerName using an implicit association join
	 * 
	 * @param ebillerName
	 *            name of eBiller
	 * @return map of eBills and eBillers with the specified ebillerName
	 */
	public List<EBill> getEBillsAndEBillersUsingImplicitAssociationJoin(
			String ebillerName) {
		return ebillDAO
				.getEBillsAndEBillersUsingImplicitAssociationJoin(ebillerName);
	}

	/**
	 * Get eBills and eBillers by eBillerName using an ordinary join
	 * 
	 * @param ebillerName
	 *            name of eBiller
	 * @return map of eBills and eBillers with the specified ebillerName
	 */
	public Map<EBill, EBiller> getEBillsAndEBillersUsingOrdinaryJoin(
			String ebillerName) {
		return ebillDAO.getEBillsAndEBillersUsingOrdinaryJoin(ebillerName);
	}

	/**
	 * Get ebills and account transactions using a left outer join for ebills
	 * that have a balance > minEBillBalance
	 * 
	 * @param minEbillBalance
	 *            minimum balance of ebill
	 * @return map of ebills and account transactions using a left outer join
	 *         for ebills that have a balance > minEBillBalance
	 */
	public Map<EBill, AccountTransaction> getEBillsAndAccountTransactionsUsingLeftOuterJoin(
			long minEbillBalance) {
		return ebillDAO
				.getEBillsAndAccountTransactionsUsingLeftOuterJoin(minEbillBalance);
	}

	/**
	 * Get ebills and account transactions using a fetch join for ebills that
	 * have a balance > minEBillBalance
	 * 
	 * @param minEbillBalance
	 *            minimum balance of ebill
	 * @return map of ebills and account transactions using a fetch join for
	 *         ebills that have a balance > minEBillBalance
	 */
	public List<EBill> getEBillsAndAccountTransactionsUsingFetchJoin(
			long minEbillBalance) {
		return ebillDAO
				.getEBillsAndAccountTransactionsUsingFetchJoin(minEbillBalance);
	}

	/**
	 * Get all ebills using native SQL and entities
	 * 
	 * @return all ebills
	 */
	public List<EBill> getEBillsUsingNativeSQLandEntities() {
		return ebillDAO.getEBillsUsingNativeSQLandEntities();
	}

	/**
	 * Get a map of ebill Ids and balances using native sql and scalar values
	 * projection
	 * 
	 * @return map of ebill Ids and balances
	 */
	public Map<Long, Double> getEBillBalances() {
		return ebillDAO.getEBillBalances();
	}

	/**
	 * Get a map of ebill Ids and ebillers using native sql and scalar and
	 * object values projection
	 * 
	 * @return map of ebill Ids and ebillers
	 */
	public Map<Long, EBiller> getEBillEBillers() {
		return ebillDAO.getEBillEBillers();
	}
}
