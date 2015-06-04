package courses.hibernate.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class EBiller {
	private long ebillerId;
	private String name;
	private String billingAddress;
	private String phone;
	private Collection<Account> accounts = new HashSet<Account>();

	// Collection of all ebill balances. Example for mapping collections
	// without associations.
	private Collection<Double> ebillBalances = new ArrayList<Double>();

	// This is the only one we will manage. The other collections are for
	// example purposes only.
	private SortedSet<EBill> ebillsSortedSet = new TreeSet<EBill>();

	// Examples for mapping purposes
	private Set<EBill> ebillsSet = new HashSet<EBill>();
	private List<EBill> ebillsBag = new ArrayList<EBill>();
	private List<EBill> ebillsList = new ArrayList<EBill>();
	private EBill[] ebillsArray;
	private Map<Long, EBill> ebillsMap = new HashMap<Long, EBill>();
	private SortedMap<Long, EBill> ebillsSortedMap = new TreeMap<Long, EBill>();

	/**
	 * Get ebillerId
	 * 
	 * @return ebillerId
	 */
	public long getEbillerId() {
		return ebillerId;
	}

	/**
	 * Set ebillerId
	 * 
	 * @param ebillerId
	 */
	public void setEbillerId(long ebillerId) {
		this.ebillerId = ebillerId;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get billingAddress
	 * 
	 * @return billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Set billingAddress
	 * 
	 * @param billingAddress
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * Get phone
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set phone
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get accounts
	 * 
	 * @return accounts
	 */
	public Collection<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Set accounts
	 * 
	 * @param accounts
	 */
	protected void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * Add account to accounts. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param account
	 *            account to be added
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
		if (!account.getEbillers().contains(this)) {
			account.addEbiller(this);
		}
	}

	/**
	 * Remove account from accounts. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param account
	 *            account to be removed
	 */
	public void removeAccount(Account account) {
		this.accounts.remove(account);
		if (account.getEbillers().contains(this)) {
			account.removeEbiller(this);
		}
	}

	/**
	 * Get ebillsSortedSet
	 * 
	 * @return ebillsSortedSet
	 */
	public Set<EBill> getEbillsSortedSet() {
		return ebillsSortedSet;
	}

	/**
	 * Set ebillsSortedSet
	 * 
	 * @param ebillsSortedSet
	 */
	protected void setEbillsSortedSet(SortedSet<EBill> ebillsSortedSet) {
		this.ebillsSortedSet = ebillsSortedSet;
	}

	/**
	 * Add ebill to ebills. Maintain both sides of bidirectional relationship.
	 * 
	 * @param ebill
	 *            ebill to be added
	 */
	public void addToEbillSortedSet(EBill ebill) {
		ebillsSortedSet.add(ebill);	
		if (!ebill.getEbiller().equals(this)) {
			ebill.getEbiller().getEbillsSortedSet().remove(ebill);
			ebill.setEbiller(this);
		}
	}

	/**
	 * Remove ebill from ebills. Maintain both sides of bidirectional
	 * relationship.
	 * 
	 * @param ebill
	 *            ebill to be removed
	 */
	public void removeFromEbillSortedSet(EBill ebill) {
		ebillsSortedSet.remove(ebill);
		if (ebill.getEbiller().equals(this)) {
			ebill.setEbiller(null);
		}
	}

	/**
	 * Get ebillsSet
	 * 
	 * @return ebillsSet
	 */
	public Set<EBill> getEbillsSet() {
		return ebillsSet;
	}

	/**
	 * Set ebillsSet
	 * 
	 * @param ebillsSet
	 */
	public void setEbillsSet(Set<EBill> ebillsSet) {
		this.ebillsSet = ebillsSet;
	}

	/**
	 * Get ebillsBag
	 * 
	 * @return ebillsBag
	 */
	public List<EBill> getEbillsBag() {
		return ebillsBag;
	}

	/**
	 * Set ebillsBag
	 * 
	 * @param ebillsBag
	 */
	public void setEbillsBag(List<EBill> ebillsBag) {
		this.ebillsBag = ebillsBag;
	}

	/**
	 * Get ebillsList
	 * 
	 * @return ebillsList
	 */
	public List<EBill> getEbillsList() {
		return ebillsList;
	}

	/**
	 * Set ebillsList
	 * 
	 * @param ebillsList
	 */
	public void setEbillsList(List<EBill> ebillsList) {
		this.ebillsList = ebillsList;
	}

	/**
	 * Get ebillsArray
	 * 
	 * @return ebillsArray
	 */
	public EBill[] getEbillsArray() {
		return ebillsArray;
	}

	/**
	 * Set ebillsArray
	 * 
	 * @param ebillsArray
	 */
	public void setEbillsArray(EBill[] ebillsArray) {
		this.ebillsArray = ebillsArray;
	}

	/**
	 * Get ebillsMap
	 * 
	 * @return ebillsMap
	 */
	public Map<Long, EBill> getEbillsMap() {
		return ebillsMap;
	}

	/**
	 * Set ebillsMap
	 * 
	 * @param ebillsMap
	 */
	public void setEbillsMap(Map<Long, EBill> ebillsMap) {
		this.ebillsMap = ebillsMap;
	}

	/**
	 * Get ebillsSortedMap
	 * 
	 * @return ebillsSortedMap
	 */
	public Map<Long, EBill> getEbillsSortedMap() {
		return ebillsSortedMap;
	}

	/**
	 * Set ebillsSortedMap
	 * 
	 * @param ebillsSortedMap
	 */
	public void setEbillsSortedMap(SortedMap<Long, EBill> ebillsSortedMap) {
		this.ebillsSortedMap = ebillsSortedMap;
	}

	/**
	 * Get ebillBalances
	 * 
	 * @return ebillBalances
	 */
	public Collection<Double> getEbillBalances() {
		return ebillBalances;
	}

	/**
	 * Set ebillBalances
	 * 
	 * @param ebillBalances
	 */
	public void setEbillBalances(Collection<Double> ebillBalances) {
		this.ebillBalances = ebillBalances;
	}

	/**
     * @return 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);
		sb.append("\n----EBILLER----\n");
		sb.append("ebillerId=").append(ebillerId).append("\n");
		sb.append("name=").append(name).append("\n");
		sb.append("billingAddress=").append(billingAddress).append("\n");
		sb.append("phone=").append(phone).append("\n");
		sb.append("eBillBalances=").append(ebillBalances).append("\n");
		if (accounts != null && accounts.isEmpty() == false) {
			sb.append("accounts=");
			for (Account account : accounts) {
				sb.append((account == null) ? "null" : account.getAccountId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsSet != null && ebillsSet.isEmpty() == false) {
			sb.append("ebillsSet=");
			for (EBill ebill : ebillsSet) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsMap != null && ebillsMap.isEmpty() == false) {
			sb.append("ebillsMap=");
			for (EBill ebill : ebillsMap.values()) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsList != null && ebillsList.isEmpty() == false) {
			sb.append("ebillsList=");
			for (EBill ebill : ebillsList) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsArray != null && ebillsArray.length > 0) {
			sb.append("ebillsArray=");
			for (EBill ebill : ebillsArray) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsBag != null && ebillsBag.isEmpty() == false) {
			sb.append("ebillsBag=");
			for (EBill ebill : ebillsBag) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsSortedSet != null && ebillsSortedSet.isEmpty() == false) {
			sb.append("ebillsSortedSet=");
			for (EBill ebill : ebillsSortedSet) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		if (ebillsSortedMap != null && ebillsSortedMap.isEmpty() == false) {
			sb.append("ebillsSortedMap=");
			for (EBill ebill : ebillsSortedMap.values()) {
				sb.append((ebill == null) ? "null" : ebill.getEbillId());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		sb.append("----EBILLER----\n");
		return sb.toString();
	}

	/**n sb.toString();
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
				+ ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + (int) (ebillerId ^ (ebillerId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		if (!(obj instanceof EBiller))
			return false;
		EBiller other = (EBiller) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (ebillerId != other.ebillerId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
}
