package courses.hibernate.vo;

/**
 * Domain object representing an Electronic Account Transaction
 */
public class ElectronicAccountTransaction extends AccountTransaction {

	private String ipAddress;
	private EBill ebill;

	/**
	 * Get ebill
	 * 
	 * @return ebill
	 */
	public EBill getEbill() {
		return ebill;
	}

	/**
	 * Set ebill. Maintain both sides of bidirectional relationship.
	 * 
	 * @param ebill
	 */
	public void setEbill(EBill ebill) {
		this.ebill = ebill;
		if (ebill != null
				&& (ebill.getAccountTransaction() == null || !ebill
						.getAccountTransaction().equals(this))) {
			ebill.setAccountTransaction(this);
		}
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----ELECTRONIC ACCOUNT TRANSACTION----\n");
		sb.append(super.toString());
		sb.append("ebill=" + ((ebill == null) ? "null" : ebill.getEbillId()) + "\n");
		sb.append("ipAddress=" + ((ipAddress == null) ? "null" : ipAddress) + "\n");
		sb.append("----ELECTRONIC ACCOUNT TRANSACTION----\n");
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ebill == null) ? 0 : ebill.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectronicAccountTransaction other = (ElectronicAccountTransaction) obj;
		if (ebill == null) {
			if (other.ebill != null)
				return false;
		} else if (!ebill.equals(other.ebill))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		return true;
	}

}
