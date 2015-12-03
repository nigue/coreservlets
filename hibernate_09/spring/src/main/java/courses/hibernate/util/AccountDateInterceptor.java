package courses.hibernate.util;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import courses.hibernate.vo.Account;

/**
 * Account Date Interceptor -- sets creation date on insert and updateDate on
 * update
 * 
 * @author Owner
 * 
 */
public class AccountDateInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = -5390941533971845293L;

	/**
	 * @{inheritDoc
	 */
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		if (entity instanceof Account) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (propertyNames[i].equalsIgnoreCase("updateDate")) {
					currentState[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @{inheritDoc
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Account) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (propertyNames[i].equalsIgnoreCase("creationDate")) {
					state[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

}