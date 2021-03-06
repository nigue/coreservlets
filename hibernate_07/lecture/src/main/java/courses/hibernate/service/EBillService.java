package courses.hibernate.service;

import java.util.List;

import courses.hibernate.dao.EBillDAO;
import courses.hibernate.vo.EBill;

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
	 * Get all ebills for an ebiller name
	 * @param ebillerName name of ebiller
	 * @return all ebills for an ebiller
	 */
	public List<EBill> getEBills(String ebillerName){
		return ebillDAO.getEBills(ebillerName);
	}

	/**
	 * Get all ebills for an ebiller name and load account
	 * 
	 * @param ebillerName
	 *            name of ebiller
	 * @return all ebills for an ebiller
	 */
	public List<EBill> getEBillsAndLoadAccount(String ebillerName) {
		return ebillDAO.getEBillsAndLoadAccount(ebillerName);
	}
}
