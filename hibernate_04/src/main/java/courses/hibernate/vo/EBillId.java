package courses.hibernate.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object that represents an EBillId
 */
public class EBillId implements Comparable<EBillId>, Serializable {

	private static final long serialVersionUID = -5489239453849885486L;

	private long accountId;
	private long ebillerId;
	private Date dueDate;

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 */
	protected void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the ebillerId
	 */
	public long getEbillerId() {
		return ebillerId;
	}

	/**
	 * @param ebillerId
	 *            the ebillerId to set
	 */
	protected void setEbillerId(long ebillerId) {
		this.ebillerId = ebillerId;
	}

	/**
	 * Get dueDate
	 * 
	 * @return dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Set dueDate
	 * 
	 * @param dueDate
	 */
	protected void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----EBILL ID----\n");
		sb.append("ebillerId=" + ebillerId + "\n");
		sb.append("accountId=" + accountId + "\n");
		sb.append("dueDate=" + dueDate + "\n");
		sb.append("----EBILL ID----\n");
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ebillerId ^ (ebillerId >>> 32));
		result = prime * result + (int) (accountId ^ (ebillerId >>> 32));
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EBill))
			return false;
		EBillId other = (EBillId) obj;
		if (ebillerId != other.ebillerId)
			return false;
		if (accountId != other.accountId)
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		return true;
	}

	/**
	 * @see java.util.Comparator#compare(Object)
	 */
	@Override
	public int compareTo(EBillId ebillIdToCompare) {
		return (this.getDueDate().compareTo(ebillIdToCompare.getDueDate()));
	}

}
