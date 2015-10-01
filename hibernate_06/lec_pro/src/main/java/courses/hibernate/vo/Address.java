package courses.hibernate.vo;

import java.io.Serializable;

/**
 * Domain object representing an Address
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 3420921615558515213L;

	private AccountOwner accountOwner;
	private String streetAddress;
	private String city;
	private String state;
	private ZipCode zipCode;

	public void setAccountOwner(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}

	public AccountOwner getAccountOwner() {
		return accountOwner;
	}

	/**
	 * Get streetAddress
	 * 
	 * @return streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * Set streetAddress
	 * 
	 * @param streetAddress
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * Get city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set city
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get state
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Set state
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Get zipCode
	 * 
	 * @return zipCode
	 */
	public ZipCode getZipCode() {
		return zipCode;
	}

	/**
	 * Set zipCode
	 * 
	 * @param zipCode
	 */
	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----ADDRESS----\n");
		sb.append("streetAddress=" + streetAddress + "\n");
		sb.append("city=" + city + "\n");
		sb.append("state=" + state + "\n");
		sb.append("zipCode=" + zipCode + "\n");
		sb.append("----ADDRESS----\n");
		return sb.toString();
	}

}
