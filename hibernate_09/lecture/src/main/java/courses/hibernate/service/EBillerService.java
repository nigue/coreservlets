package courses.hibernate.service;

import java.util.List;

import courses.hibernate.dao.EBillerDAO;
import courses.hibernate.vo.EBiller;

/**
 * Service layer for EBiller
 */
public class EBillerService {
	EBillerDAO ebillerDAO = new EBillerDAO();

	/**
	 * Create a new ebiller or update an existing one
	 * 
	 * @param ebiller
	 *            ebiller to be persisted
	 */
	public void saveOrUpdateEBiller(EBiller ebiller) {
		ebillerDAO.saveOrUpdateEBiller(ebiller);
	}

	/**
	 * Retrieve an ebiller
	 * 
	 * @param ebillerId
	 *            identifier of the ebiller to be retrieved
	 * @return ebiller represented by the identifier provided
	 */
	public EBiller getEBiller(long ebillerId) {
		return ebillerDAO.getEBiller(ebillerId);
	}

	/**
	 * Delete ebiller
	 * 
	 * @param ebiller
	 *            ebiller to be deleted
	 */
	public void deleteEBiller(EBiller ebiller) {
		ebillerDAO.deleteEBiller(ebiller);
	}

	/**
	 * Get ebillers who have ebills with the requested balance using HQL
	 * approach
	 * 
	 * @param eBillBalance
	 *            balance of ebill
	 * @return ebillers who have ebills with the requested balance
	 */
	public List<EBiller> getEBillersUsingHQL(double eBillBalance) {
		return ebillerDAO.getEBillersUsingHQL(eBillBalance);
	}
}
