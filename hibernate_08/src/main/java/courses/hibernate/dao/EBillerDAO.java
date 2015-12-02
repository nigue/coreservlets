package courses.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.EBill;
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

	/**
	 * Get ebillers who have ebills with the requested balance using QBC
	 * approach
	 * 
	 * @param eBillBalance
	 *            balance of ebill
	 * @return ebillers who have ebills with the requested balance
	 */
	@SuppressWarnings("unchecked")
	public List<EBiller> getEBillersUsingQBC(double eBillBalance) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		return (List<EBiller>) session.createCriteria(
				EBiller.class).createAlias("ebills", "ebills").add(
				Restrictions.eq("ebills.balance",eBillBalance)).list();
	}

	/**
	 * Get ebillers who have ebills with the requested balance using HQL
	 * approach
	 * 
	 * @param eBillBalance
	 *            balance of ebill
	 * @return ebillers who have ebills with the requested balance
	 */
	@SuppressWarnings("unchecked")
	public List<EBiller> getEBillersUsingHQL(double eBillBalance) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		return (List<EBiller>) session.createQuery("select ebiller from " +
				  "EBiller ebiller join ebiller.ebills ebills where " + 
				  "ebills.balance = :balance")
				  .setParameter("balance", eBillBalance).list();
	}
	
	/**
	 * Get eBills and eBillers by ebill balance using ordinary joins
	 * 
	 * @param ebillerName
	 *            name of eBiller
	 * @return map of eBills and eBillers with the specified ebillerName
	 */
	@SuppressWarnings("unchecked")
	public Map<EBiller, List<EBill>> getEbillsUsingOrdinaryJoins(
			long ebillBalance) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Object[]> listOfRowValues = (List<Object[]>) session.createQuery(
				" from EBiller ebiller join ebiller.ebills ebill"
						+ " where ebill.balance >= " + ebillBalance).list();

		Map<EBiller, List<EBill>> ebillerEBillMap = new HashMap<EBiller, List<EBill>>();
		for (Object[] singleRowValues : listOfRowValues) {
			EBiller ebiller = (EBiller) singleRowValues[0];
			EBill ebill = (EBill) singleRowValues[1];
			if (!ebillerEBillMap.containsKey(ebiller)) {
				ebillerEBillMap.put(ebiller, new ArrayList<EBill>());
			}
			ebillerEBillMap.get(ebiller).add(ebill);
		}
		return ebillerEBillMap;
	}
}
