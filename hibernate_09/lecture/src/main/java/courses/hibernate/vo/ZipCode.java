package courses.hibernate.vo;

/**
 * Domain object representing an ZipCode
 */
public class ZipCode {
	private String zip;
	private String plus4;

	/**
	 * Set zip
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Get zip
	 * 
	 * @return zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Set plus4
	 * 
	 * @param plus4
	 */
	public void setPlus4(String plus4) {
		this.plus4 = plus4;
	}

	/**
	 * Get plus4
	 * 
	 * @return plus4
	 */
	public String getPlus4() {
		return plus4;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plus4 == null) ? 0 : plus4.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		ZipCode other = (ZipCode) obj;
		if (plus4 == null) {
			if (other.plus4 != null)
				return false;
		} else if (!plus4.equals(other.plus4))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----ZIP CODE----\n");
		sb.append("zip=" + zip + "\n");
		sb.append("plus4=" + plus4 + "\n");
		sb.append("----ZIP CODE----\n");
		return sb.toString();
	}
}
