import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateStaffInfo {


// Update your user info alone here
private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/bagrawa"; // Using SERVICE_NAME

// Update your user and password info here!

private static final String user = "bagrawa";
private static final String password = "200347899";

public static void main(String[] args) {
try {
// Loading the driver. This creates an instance of the driver
// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.


Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;
			
			String staffID = null;
            String sqlSelect = null;
			      Scanner sc= new Scanner(System.in);
            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            // Create a statement instance that will be sending
            // your SQL statements to the DBMS
            
      			try{
				System.out.print("Enter staff ID "); 
      			staffID = sc.nextLine();
				
				System.out.print("Enter store ID: "); 
      			String storeID = sc.nextLine();
      			
				System.out.print("Enter name: "); 
      			String name = sc.nextLine();
				
				System.out.print("Enter age"); 
      			Integer age = sc.nextInt();
				
				System.out.print("Enter home address");
				String homeAddr= sc.nextLine();
				
				System.out.print("Enter job title");
				String jobT= sc.nextLine();
				
				System.out.print("Enter phone number");
				String phoneNumber= sc.nextLine();
				
				System.out.print("Enter email address");
				String email= sc.nextLine();
				
				System.out.print("Enter timestamp in YYYY-MM-DD format");
				String timestamp= sc.nextLine();
					
      			String sql= "UPDATE STAFF SET STOREID=%s, NAME=%s, AGE=%s, HOMEADDRESS=%s, JOBTITLE=%s, PHONENUMBER=%s, EMAILADDRESS=%s, TIMEOFEMPLOYMENT=%s WHERE STAFFID=%s";
				sqlSelect = String.format(sql,"'"+storeID+"'","'"+name+"'","'"+age+"'","'"+homeAddr+"'","'"+jobT+"'","'"+phoneNumber+"'","'"+email+"'","'"+timestamp+"'","'"+staffID+"'");
			
			
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for inputs");
            }
			
			try{
			connection.setAutoCommit(false);
  			statement.executeQuery(sqlSelect);
			connection.commit();
			System.out.println("Statement Executed");
          }
          
        catch (Exception e){
		connection.rollback();
        System.out.println("Statement not executed");
        
        }
			
			} finally {
                                
                close(statement);
                close(connection);
                
            }
} catch(Throwable oops) {
            oops.printStackTrace();
        }
}
static void close(Connection connection) {
        if(connection != null) {
            try {
            connection.close();
            } catch(Throwable whatever) {}
        }
    }
    
static void close(Statement statement) {
        if(statement != null) {
            try {
            statement.close();
            } catch(Throwable whatever) {}
        }
    }
    
    
}