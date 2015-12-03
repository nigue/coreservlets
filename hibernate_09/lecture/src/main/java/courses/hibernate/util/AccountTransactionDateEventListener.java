package courses.hibernate.util;

import java.util.Date;

import org.hibernate.HibernateException;

import courses.hibernate.vo.AccountTransaction;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

/**
 * Event listener that sets transaction dates on save/update Account Transaction
 * events
 */
public class AccountTransactionDateEventListener implements
        SaveOrUpdateEventListener {

    private static final long serialVersionUID = 7463600627397031283L;

    /**
     * @param saveOrUpdateEvent
     * @{inheritDoc
     */
    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent saveOrUpdateEvent)
            throws HibernateException {

        if (saveOrUpdateEvent.getObject() instanceof AccountTransaction) {
            AccountTransaction accountTransaction = (AccountTransaction) saveOrUpdateEvent
                    .getObject();
            accountTransaction.setTransactionDate(new Date());
        }
    }

}
