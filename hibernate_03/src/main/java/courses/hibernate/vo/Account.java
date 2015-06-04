package courses.hibernate.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Domain object representing an Account
 */
public class Account {
	public static final String ACCOUNT_TYPE_SAVINGS = "SAVINGS";
	public static final String ACCOUNT_TYPE_CHECKING = "CHECKING";

	private long accountId;
	private String accountType;
	private Date creationDate;
	private double balance;
	private Collection<EBiller> ebillers = new ArrayList<EBiller>();

	/**
	 * Get accountId
	 * 
	 * @return accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * Set accountId
	 * 
	 * @param accountId
	 */
	@SuppressWarnings("unused")
	private void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/**
	 * Get accountType
	 * 
	 * @return accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Set accountType
	 * 
	 * @param accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * Get creationDate
	 * 
	 * @return creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Set creationDate
	 * 
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Get balance
	 * 
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Set balance
	 * 
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Set ebillers
	 * 
	 * @param ebillers
	 */
	protected void setEbillers(Collection<EBiller> ebillers) {
		this.ebillers = ebillers;
	}

	/**
	 * Get ebillers
	 * 
	 * @return ebillers
	 */
	public Collection<EBiller> getEbillers() {
		return ebillers;
	}

	/**
	 * Add ebiller to ebillers. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param ebiller
	 */
	public void addEbiller(EBiller ebiller) {
		this.ebillers.add(ebiller);
		if (!ebiller.getAccounts().contains(this)) {
			ebiller.addAccount(this);
		}
	}

	/**
	 * Remove ebiller from ebillers. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param ebiller
	 */
	public void removeEbiller(EBiller ebiller) {
		this.ebillers.remove(ebiller);
		if (ebiller.getAccounts().contains(this)) {
			ebiller.removeAccount(this);
		}
	}

	/**
     * @return 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);
		sb.append("\n----ACCOUNT----\n");
		sb.append("accountId=").append(accountId).append("\n");
		sb.append("accountType=").append(accountType).append("\n");
		sb.append("creationDate=").append(creationDate).append("\n");
		sb.append("balance=").append(balance).append("\n");

		if (ebillers != null && ebillers.isEmpty() == false) {
			sb.append("ebillers=");
			for (EBiller ebiller : ebillers) {
				sb.append((ebiller == null) ? "null" : ebiller.getEbillerId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		sb.append("----ACCOUNT----\n");
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		return result;
	}

	/**
     * @return 
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		return true;
	}
}
