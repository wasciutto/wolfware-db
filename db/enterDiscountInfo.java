import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class enterDiscountInfo {


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
			
            
			String discountID = null;
            String sqlSelect = null;
			String sqlSelect1 = null;
			      Scanner sc= new Scanner(System.in);
            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            // Create a statement instance that will be sending
            // your SQL statements to the DBMS
            
      			try{
				System.out.print("Enter discount ID : "); 
      			discountID = sc.nextLine();
				
				System.out.print("Enter discount type: "); 
      			String dType = sc.nextLine();
				
				System.out.print("Enter discount value: "); 
      			String dValue = sc.nextLine();
				
				System.out.print("Enter Start Date (in YYYY-MM-DD format): ");
      			String startDate = sc.nextLine();
				
				System.out.print("Enter End Date (in YYYY-MM-DD format): ");
      			String endDate = sc.nextLine();
				
				System.out.print("Enter product ID (Please enter ID): "); 
      			String productID = sc.nextLine();
      			
				System.out.print("Enter batch ID (Please enter ID): "); 
      			String batchID = sc.nextLine();
				
      			String sql= "INSERT INTO STORE VALUES (%s,%s,%s,%s,%s)";
				sqlSelect = String.format(sql,"'"+discountID+"'","'"+dType+"'","'"+dValue+"'","'"+startDate+"'","'"+endDate+"'");
				String sql1= "INSERT INTO HASDISCOUNT VALUES (%s,%s,%s)";
				sqlSelect1 = String.format(sql1,"'"+discountID+"'","'"+productID+"'","'"+batchID+"'");
			
			
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for Store Id");
            }
			
			try{
			connection.setAutoCommit(false);
  			statement.executeQuery(sqlSelect);
			statement.executeQuery(sqlSelect1);
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