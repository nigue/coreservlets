package courses.hibernate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.AccountTransaction;
import courses.hibernate.vo.EBill;
import courses.hibernate.vo.EBiller;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;

/**
 * Data Access Object for EBill
 */
public class EBillDAO {

    /**
     * Create a new ebill or update an existing one
     *
     * @param ebill ebill to be persisted
     */
    public void saveOrUpdateEBill(EBill ebill) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(ebill);
    }

    /**
     * Retrieve an ebill from the data store
     *
     * @param ebillId identifier of the ebill to be retrieved
     * @return ebill represented by the identifier provided
     */
    public EBill getEBill(long ebillId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EBill ebill = (EBill) session.get(EBill.class, ebillId);
        return ebill;
    }

    /**
     * Delete ebill from data store
     *
     * @param ebill ebill to be deleted
     */
    public void deleteEBill(EBill ebill) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(ebill);
    }

    /**
     * Merge ebill with data store
     *
     * @param ebill ebill to be merge
     */
    public void mergeEBill(EBill ebill) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.merge(ebill);
    }

    /**
     * Reattach ebill with lock
     *
     * @param ebill ebill to be reattached
     */
    public void reattachEBillWithLock(EBill ebill) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.lock(ebill, LockMode.NONE);
    }

    /**
     * Get eBills and eBillers by eBillerName using an implicit association join
     *
     * @param ebillerName name of eBiller
     * @return map of eBills and eBillers with the specified ebillerName
     */
    @SuppressWarnings("unchecked")
    public List<EBill> getEBillsAndEBillersUsingImplicitAssociationJoin(
            String ebillerName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<EBill>) session.createQuery(
                "from EBill ebill where ebill.ebiller.name = '" + ebillerName + "'")
                .list();
    }

    /**
     * Get eBills and eBillers by eBillerName using an ordinary join
     *
     * @param ebillerName name of eBiller
     * @return map of eBills and eBillers with the specified ebillerName
     */
    @SuppressWarnings("unchecked")
    public Map<EBill, EBiller> getEBillsAndEBillersUsingOrdinaryJoin(
            String ebillerName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Object[]> listOfRowValues = (List<Object[]>) session.createQuery(
                "from EBill ebill join ebill.ebiller ebiller"
                + " where ebiller.name = '" + ebillerName + "'").list();

        Map ebillEBillerMap = new HashMap<EBill, EBiller>();
        for (Object[] singleRowValues : listOfRowValues) {
            EBill ebill = (EBill) singleRowValues[0];
            EBiller ebiller = (EBiller) singleRowValues[1];
            ebillEBillerMap.put(ebill, ebiller);
        }
        return ebillEBillerMap;
    }

    /**
     * Get ebills and account transactions using a left outer join for ebills
     * that have a balance > minEBillBalance
     *
     * @param minEbillBalance minimum balance of ebill
     * @return map of ebills and account transactions using a left outer join
     * for ebills that have a balance > minEBillBalance
     */
    @SuppressWarnings("unchecked")
    public Map<EBill, AccountTransaction> getEBillsAndAccountTransactionsUsingLeftOuterJoin(
            long minEbillBalance) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        List<Object[]> listOfRowValues = (List<Object[]>) session.createQuery(
                "from EBill ebill left join ebill.accountTransaction"
                + " where ebill.balance >= " + minEbillBalance).list();

        Map eBillAccountTransactionMap = new HashMap<EBill, AccountTransaction>();
        for (Object[] singleRowValues : listOfRowValues) {
            EBill ebill = (EBill) singleRowValues[0];
            AccountTransaction accountTransaction = (AccountTransaction) singleRowValues[1];

            eBillAccountTransactionMap.put(ebill, accountTransaction);
        }
        return eBillAccountTransactionMap;
    }

    /**
     * Get ebills and account transactions using a fetch join for ebills that
     * have a balance > minEBillBalance
     *
     * @param minEbillBalance minimum balance of ebill
     * @return map of ebills and account transactions using a fetch join for
     * ebills that have a balance > minEBillBalance
     */
    @SuppressWarnings("unchecked")
    public List<EBill> getEBillsAndAccountTransactionsUsingFetchJoin(
            long minEbillBalance) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<EBill>) session.createQuery(
                "from EBill ebill left join fetch ebill.accountTransaction"
                + " where ebill.balance >= " + minEbillBalance).list();

    }

    /**
     * Get all ebills using native SQL and entities
     *
     * @return all ebills
     */
    @SuppressWarnings("unchecked")
    public List<EBill> getEBillsUsingNativeSQLandEntities() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (List<EBill>) session.createSQLQuery("SELECT * FROM EBILL")
                .addEntity(EBill.class).list();
    }

    /**
     * Get a map of ebill Ids and balances using native sql and scalar values
     * projection
     *
     * @return map of ebill Ids and balances
     */
    @SuppressWarnings("unchecked")
    public Map<Long, Double> getEBillBalances() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Object[]> listOfRowValues = (List<Object[]>) session
                .createSQLQuery(
                        "SELECT EB.EBILL_ID AS ID, EB.BALANCE AS BALANCE FROM EBILL EB")
                .addScalar("id", LongType.INSTANCE).addScalar("balance",
                        DoubleType.INSTANCE).list();

        Map<Long, Double> ebillBalances = new HashMap<Long, Double>();
        for (Object[] singleRowValues : listOfRowValues) {
            long id = (Long) singleRowValues[0];
            double balance = (Double) singleRowValues[1];
            ebillBalances.put(id, balance);
        }
        return ebillBalances;
    }

    /**
     * Get a map of ebill Ids and ebillers using native sql and scalar and
     * object values projection
     *
     * @return map of ebill Ids and ebillers
     */
    @SuppressWarnings("unchecked")
    public Map<Long, EBiller> getEBillEBillers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Object[]> listOfRowValues = (List<Object[]>) session
                .createSQLQuery(
                        "SELECT E.EBILL_ID AS ID, EBLR.* FROM EBILL E, EBILLER EBLR")
                .addScalar("id", LongType.INSTANCE).addEntity("EBLR",
                        EBiller.class).list();

        Map<Long, EBiller> ebillEbillers = new HashMap<Long, EBiller>();
        for (Object[] singleRowValues : listOfRowValues) {
            long id = (Long) singleRowValues[0];
            EBiller ebiller = (EBiller) singleRowValues[1];
            ebillEbillers.put(id, ebiller);
        }
        return ebillEbillers;
    }
}
