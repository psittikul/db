import java.sql.*;
import javax.sql.rowset.*;

/**
 * Main class for all operations between Employee objects and the database, i.e. establishing the connection,
 * handling information
 * 7/12/2016
 * @author Patricia Sittikul
 *
 */
public class EmployeeBean {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";
	static final String DB_USER = "root";
	static final String DB_PASS = "JimYosh4064";
	static ResultSet rs;
	static Connection connect;
	static Statement state;

	
	public static void main (String [] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//STEP 2: Register JDBC Driver
			Class.forName(JDBC_DRIVER);
			
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT personId, firstName, lastName, email, company FROM Employees";
			rs = stmt.executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while (rs.next()) {
				int id = rs.getInt("personId");
				String comp = rs.getString("company");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");
			
				System.out.print("Record #: " + id);
				System.out.print(" " + first + " ");
				System.out.print(last + " ");
				System.out.print(email);
				System.out.print(" " + comp);
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			}catch(SQLException se2) {
				//nothing we can do
			}
			try {
				if (conn != null) 
					conn.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}

	public Employee create(Employee emp) {
		try {
			Class.forName(JDBC_DRIVER);
			connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			String query = ("insert into emp.Employees values(default, ?, ?, ?, ?)"); 
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(2, emp.getFirstName());
			preparedStmt.setString(3,  emp.getLastName());
			preparedStmt.setString(4, emp.getEmail());
			preparedStmt.setString(5, emp.getCompany());
			
			//execute the prepared statement
			preparedStmt.execute();
			connect.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return emp;
	}

	/*public Employee getCurrent() {
		Employee emp = new Employee();
		try {
			Class.forName(JDBC_DRIVER);
			connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement statement = connect.createStatement();
			String query = "SELECT * from emp.employees";
			rs = statement.executeQuery(query);
			rs.moveToCurrentRow();
			
			emp.setPersonId(rs.getInt("personId"));
			emp.setFirstName(rs.getString("firstName"));
			emp.setLastName(rs.getString("lastName"));
			emp.setEmail(rs.getString("email"));
			emp.setCompany(rs.getString("company"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		
		return emp;
	} */

	public void delete() {
		try {
			rs.moveToCurrentRow();
			rs.deleteRow();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	public Employee update(Employee emp) {
		try {

			rs.updateString("firstName", emp.getFirstName());
			rs.updateString("lastName", emp.getLastName());
			rs.updateString("email", emp.getEmail());
			rs.updateString("company", emp.getCompany());
			rs.updateRow();
			rs.moveToCurrentRow();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return emp;
	}
}
