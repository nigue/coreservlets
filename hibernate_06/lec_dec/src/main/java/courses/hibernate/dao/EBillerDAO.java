package courses.hibernate.dao;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBiller;

/**
 * Data Access Object for EBiller
 */
public class EBillerDAO {

	/**
	 * Create a new ebiller or update an existing one
	 * 
	 * @param ebiller
	 *            ebiller to be persisted
	 */
	public void saveOrUpdateEBiller(EBiller ebiller) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(ebiller);
	}

	/**
	 * Retrieve an ebiller from the data store
	 * 
	 * @param ebillerId
	 *            identifier of the ebiller to be retrieved
	 * @return ebiller represented by the identifier provided
	 */
	public EBiller getEBiller(long ebillerId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EBiller ebiller = (EBiller) session.get(EBiller.class, ebillerId);
		return ebiller;
	}

	/**
	 * Delete ebiller from data store
	 * 
	 * @param ebiller
	 *            ebiller to be deleted
	 */
	public void deleteEBiller(EBiller ebiller) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(ebiller);
	}
}
