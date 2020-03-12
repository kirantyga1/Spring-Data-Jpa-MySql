package springdatajpamysql.springdatajpamysql.shared;

import java.io.Serializable;
import java.util.Date;

public class PlayerDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3428133510767792902L;
	private String firstName;
	private String lastName;
	private Date lastUpdated;

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
