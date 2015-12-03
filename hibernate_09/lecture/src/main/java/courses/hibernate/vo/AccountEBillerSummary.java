package courses.hibernate.vo;

/**
 * Data Transfer Object representing an AccountEBillerSummary
 */
public class AccountEBillerSummary {
	private long accountId;
	private long ebillerId;
	private long count;
	private double averageBalance;

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(long accountId) {
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
	public void setEbillerId(long ebillerId) {
		this.ebillerId = ebillerId;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the averageBalance
	 */
	public double getAverageBalance() {
		return averageBalance;
	}

	/**
	 * @param averageBalance
	 *            the averageBalance to set
	 */
	public void setAverageBalance(double averageBalance) {
		this.averageBalance = averageBalance;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----ACCOUNT EBILLER SUMMARY----\n");
		sb.append("accountId=" + accountId + "\n");
		sb.append("ebillerId=" + ebillerId + "\n");
		sb.append("count=" + count + "\n");
		sb.append("averageBalance=" + averageBalance + "\n");
		sb.append("----ACCOUNT EBILLER SUMMARY----\n");
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#hashCode(java.lang.Object)
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(averageBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + (int) (ebillerId ^ (ebillerId >>> 32));
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountEBillerSummary other = (AccountEBillerSummary) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(averageBalance) != Double
				.doubleToLongBits(other.averageBalance))
			return false;
		if (count != other.count)
			return false;
		if (ebillerId != other.ebillerId)
			return false;
		return true;
	}

}
