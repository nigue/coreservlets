package courses.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBiller;

/**
 * Data Access Object for EBiller
 */
public class EBillerDAO {

	/**
	 * Get ebillers who have ebills with the requested balance using HQL
	 * approach
	 * 
	 * **************************************************************
	 * SOLUTION FOR EXERCISE 8:MEDIUM
	 * PART 1 of 1
	 * Use the Hibernate Query Language to retrieve 
	 * eBillers who issued eBills for $500
	 * **************************************************************	 
     *	 
	 * @param eBillBalance
	 *            balance of ebill
	 * @return ebillers who have ebills with the requested balance
	 */
	@SuppressWarnings("unchecked")
	public List<EBiller> getEBillersUsingHQL(double eBillBalance) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		return (List<EBiller>) session.createQuery(
				"select ebiller from "
						+ "EBiller ebiller join ebiller.ebills ebills where "
						+ "ebills.balance = :balance").setParameter("balance",
				eBillBalance).list();
	}


	
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
