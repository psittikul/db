/**
 * The Employee object type to be stored in the DB
 * 7/12/2016
 * @author Patricia Sittikul
 *
 */
public class Employee extends Person{
	private int personId;
	private String firstName;
	private String lastName;
	private String email;
	private String company;
	
	/**
	 * Constructors
	 */
	public Employee () {
	}
	
	public Employee (int pid, String fn, String ln, String email, String comp) {
		this.personId = pid;
		this.firstName = fn;
		this.lastName = ln;
		this.email = email;
		this.company = comp;
	}
}
