import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class enterSupplierInfo {


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
			
            
			String supplierID = null;
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
				System.out.print("Enter suppplier ID: "); 
      			supplierID = sc.nextLine();
				
				System.out.print("Enter supplier name: "); 
      			String sname = sc.nextLine();
						
				System.out.print("Enter email address");
				String email= sc.nextLine();
				
				System.out.print("Enter phone number");
				String phoneNumber= sc.nextLine();
				
				System.out.print("Enter location");
				String location= sc.nextLine();
				
				String sql= "INSERT INTO SUPPLIERS VALUES (%s,%s,%s,%s,%s)";
				sqlSelect = String.format(sql,"'"+supplierID+"'","'"+sname+"'","'"+email+"'"+,"'"+phoneNumber+"'","'"+location+"'");
				
			
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for input");
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