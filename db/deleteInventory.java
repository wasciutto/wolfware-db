import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteInventory {



private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri"; 


private static final String user = "pattri";
private static final String password = "200226336";

public static void main(String[] args) {
try {

Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;
            
            
			
			String productID = null;
			String batchID = null;
			
			String staffID = null;
			
			String sqlSelect1 = null;
			String sqlSelect2 = null;
			Scanner sc= new Scanner(System.in);
            
			try {
            
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            
            
      			try{
					System.out.print("Enter Your Staff ID:"); 
        			staffID = sc.nextLine();
        								
  				    System.out.print("Enter product ID:"); 
        			productID = sc.nextLine();
        			
					System.out.print("Enter batch ID:"); 
        			batchID = sc.nextLine();
					
					String sql = "DELETE FROM PRODUCTINVENTORY WHERE PRODUCTID=%s AND BATCHID = %s";
					sqlSelect1 = String.format(sql,"'"+productID+"'", "'"+batchID+"'");
					
					sql = "INSERT INTO MAINTAINS VALUES('DELETE',%s,%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");
            }try{
				connection.setAutoCommit(false);
				statement.executeQuery(sqlSelect1);
				statement.executeQuery(sqlSelect2);
				connection.commit();
				System.out.println("Product removed from inventory.");
				}catch (Exception e){
				connection.rollback();
				System.out.println("Product removed from added to inventory");
				}	
				System.out.println("*************************************************");
			}finally {               
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
    
 static void close(ResultSet result) {
        if(result != null) {
            try {
            result.close();
            } catch(Throwable whatever) {}
        }
    }
    
    
}