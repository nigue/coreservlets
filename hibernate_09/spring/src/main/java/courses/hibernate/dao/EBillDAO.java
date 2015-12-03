package courses.hibernate.dao;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import courses.hibernate.vo.EBill;

/**
 * Data Access Object for EBill
 */
public class EBillDAO {

	private SessionFactory sessionFactory;

	/**
	 * Constructor to create EBillDAO. Used by Spring
	 * 
	 * @param sessionFactory
	 *            Hibernate session factory
	 */
	public EBillDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Create a new ebill or update an existing one
	 * 
	 * @param ebill
	 *            ebill to be persisted
	 */
	public void saveOrUpdateEBill(EBill ebill) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ebill);
	}

	/**
	 * Retrieve an ebill from the data store
	 * 
	 * @param ebillId
	 *            identifier of the ebill to be retrieved
	 * @return ebill represented by the identifier provided
	 */
	public EBill getEBill(long ebillId) {
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
		session.delete(ebill);
	}

	/**
	 * Merge ebill with data store
	 * 
	 * @param ebill
	 *            ebill to be merge
	 */
	public void mergeEBill(EBill ebill) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(ebill);
	}

	/**
	 * Reattach ebill with lock
	 * 
	 * @param ebill
	 *            ebill to be reattached
	 */
	public void reattachEBillWithLock(EBill ebill) {
		Session session = sessionFactory.getCurrentSession();
		session.lock(ebill, LockMode.NONE);
	}
}
