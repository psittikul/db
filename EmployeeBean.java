import java.sql.*;
import javax.sql.rowset.CachedRowSet;

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
	
	private CachedRowSet rowSet;
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
			ResultSet rs = stmt.executeQuery(sql);
			
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
			
			//STEP 6: Clean up environment
			rs.close();
			stmt.close();
			conn.close();
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
			rowSet.moveToInsertRow();
			rowSet.updateInt("personId", emp.getPersonId());
			rowSet.updateString("firstName", emp.getFirstName());
			rowSet.updateString("lastName", emp.getLastName());
			rowSet.updateString("email", emp.getEmail());
			rowSet.updateString("company", emp.getCompany());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				emp = null;
			} catch (SQLException e) {
				
			}
			ex.printStackTrace();
		}
		return emp;
	}

	public Employee getCurrent() {
		Employee emp = new Employee();
		try {
			rowSet.moveToCurrentRow();
			emp.setPersonId(rowSet.getInt("personId"));
			emp.setFirstName(rowSet.getString("firstName"));
			emp.setLastName(rowSet.getString("lastName"));
			emp.setEmail(rowSet.getString("email"));
			emp.setCompany(rowSet.getString("company"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return emp;
	}

	public void delete() {
		try {
			rowSet.moveToCurrentRow();
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) { }
			ex.printStackTrace();
		}
		
	}

	public Employee update(Employee emp) {
		try {
			rowSet.updateString("firstName", emp.getFirstName());
			rowSet.updateString("lastName", emp.getLastName());
			rowSet.updateString("email", emp.getEmail());
			rowSet.updateString("company", emp.getCompany());
			rowSet.updateRow();
			rowSet.moveToCurrentRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
				
			}
			ex.printStackTrace();
		}
		return emp;
	}
}
