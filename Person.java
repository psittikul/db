
/**
 * Abstract class defining a "person" data structure for all clients, company contacts, etc.
 * 7/12/2016
 * @author Patricia Sittikul
 *
 */
public abstract class Person {
	private String firstName, lastName, email, company;
	private String personId;
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String string) {
		personId = string;
	}

	
}
