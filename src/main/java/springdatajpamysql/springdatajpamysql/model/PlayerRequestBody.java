package springdatajpamysql.springdatajpamysql.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlayerRequestBody {

	@NotBlank(message = "first name can't be blank")
	@Size(min = 2, message = "firstName must not be less than 2 characters")
	private String firstName;

	@NotBlank(message = "last name can't be blank")
	@Size(min = 2, message = "lastName must not be less than 2 characters")
	private String lastName;

	@NotNull
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
