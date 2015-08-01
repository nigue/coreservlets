package courses.hibernate.dao;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBillId;

/**
 * Data Access Object for EBill
 */
public class EBillDAO {

	/**
	 * Create a new ebill or update an existing one
	 * 
	 * @param ebill
	 *            ebill to be persisted
	 */
	public void saveOrUpdateEBill(EBill ebill) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(ebill);
	}

	/**
	 * Retrieve an ebill from the data store
	 * 
	 * @param ebillId
	 *            identifier of the ebill to be retrieved
	 * @return ebill represented by the identifier provided
	 */
	public EBill getEBill(EBillId ebillId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EBill ebill = (EBill) session.get(EBill.class, ebillId);
		return ebill;
	}

	/**
	 * Delete ebill from data store
	 * 
	 * @param ebill
	 *            ebill to be deleted
	 */
	public void deleteEBill(EBill ebill) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(ebill);
	}
	
	/**
	 * Merge ebill with data store
	 * 
	 * @param ebill
	 *            ebill to be merge
	 */
	public void mergeEBill(EBill ebill) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.merge(ebill);
	}
}
