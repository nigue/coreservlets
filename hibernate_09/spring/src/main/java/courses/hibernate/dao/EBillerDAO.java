package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import courses.hibernate.vo.EBiller;

/**
 * Data Access Object for EBiller
 */
public class EBillerDAO {

	private SessionFactory sessionFactory;

	/**
	 * Constructor to create EBillerDAO. Used by Spring
	 * 
	 * @param sessionFactory
	 *            Hibernate session factory
	 */
	public EBillerDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Create a new ebiller or update an existing one
	 * 
	 * @param ebiller
	 *            ebiller to be persisted
	 */
	public void saveOrUpdateEBiller(EBiller ebiller) {
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
		session.delete(ebiller);
	}

	/**
	 * Get ebillers who have ebills with the requested balance using HQL
	 * approach
	 * 
	 * @param eBillBalance
	 *            balance of ebill
	 * @return ebillers who have ebills with the requested bsalance
	 */
	@SuppressWarnings("unchecked")
	public List<EBiller> getEBillersUsingHQL(double eBillBalance) {
		Session session = sessionFactory.getCurrentSession();

		return (List<EBiller>) session.createQuery(
				"select ebiller from "
						+ "EBiller ebiller join ebiller.ebills ebills where "
						+ "ebills.balance = :balance").setParameter("balance",
				eBillBalance).list();
	}

}
