package courses.hibernate.vo;

/**
 * Domain object representing an Account Owner
 */
public class AccountOwner {
	private long accountOwnerId;
	private String lastName;
	private String firstName;
	private String socialSecurityNumber;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String homePhone;
	private String cellPhone;

	/**
	 * Get accountOwnerId
	 * 
	 * @return accountOwnerId
	 */
	public long getAccountOwnerId() {
		return accountOwnerId;
	}

	/**
	 * Set accountOwnerId
	 * 
	 * @param accountOwnerId
	 */
	public void setAccountOwnerId(long accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	/**
	 * Get lastName
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set lastName
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get firstName
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set firstName
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get socialSecurityNumber
	 * 
	 * @return socialSecurityNumber
	 */
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	/**
	 * Set socialSecurityNumber
	 * 
	 * @param socialSecurityNumber
	 */
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
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
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Set zipCode
	 * 
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Get homePhone
	 * 
	 * @return homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * Set homePhone
	 * 
	 * @param homePhone
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * Set cellPhone
	 * 
     * @return 
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * Set cellPhone
	 * 
	 * @param cellPhone
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
     * @return 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (accountOwnerId ^ (accountOwnerId >>> 32));
		result = prime * result
				+ ((cellPhone == null) ? 0 : cellPhone.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime
				* result
				+ ((socialSecurityNumber == null) ? 0 : socialSecurityNumber
						.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		AccountOwner other = (AccountOwner) obj;
		if (accountOwnerId != other.accountOwnerId)
			return false;
		if (cellPhone == null) {
			if (other.cellPhone != null)
				return false;
		} else if (!cellPhone.equals(other.cellPhone))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homePhone == null) {
			if (other.homePhone != null)
				return false;
		} else if (!homePhone.equals(other.homePhone))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (socialSecurityNumber == null) {
			if (other.socialSecurityNumber != null)
				return false;
		} else if (!socialSecurityNumber.equals(other.socialSecurityNumber))
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
     * @return 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);
		sb.append("\n----ACCOUNT OWNER----\n");
		sb.append("accountOwnerId=").append(accountOwnerId).append("\n");
		sb.append("lastName=").append(lastName).append("\n");
		sb.append("firstName=").append(firstName).append("\n");
		sb.append("socialSecurityNumber=").append(socialSecurityNumber).append("\n");
		sb.append("streetAddress=").append(streetAddress).append("\n");
		sb.append("city=").append(city).append("\n");
		sb.append("state=").append(state).append("\n");
		sb.append("zipCode=").append(zipCode).append("\n");
		sb.append("homePhone=").append(homePhone).append("\n");
		sb.append("cellPhone=").append(cellPhone).append("\n");
		sb.append("----ACCOUNT OWNER----\n");
		return sb.toString();
	}
}
