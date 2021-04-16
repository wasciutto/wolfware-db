import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class transferProducts {



private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/upnadupa"; 


private static final String user = "upnadupa";
private static final String password = "Momos";

public static void main(String[] args) {
try {


Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;
           
            
			String productID = null;
			String batchID = null;
			String staffID = null;
			String storeID1 = null;
            String storeID2 = null;
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
					
					System.out.print("Enter Store ID of store to transfer product from:"); 
        			storeID1 = sc.nextLine();
					
					System.out.print("Enter Store ID of store to transfer product to:"); 
        			storeID2 = sc.nextLine();
        			
					
					String sql = "UPDATE HASPRODUCTINVENTORY SET STOREID=%s  WHERE PRODUCTID=%s AND STOREID=%s AND BATCHID=%s";
					sqlSelect1 = String.format(sql, "'"+storeID2+"'", "'"+productID+"'","'"+storeID1+"'","'"+batchID+"'");
					
					
					sql = "INSERT INTO MAINTAINS VALUES('Transfer',%s,%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
					
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");
            }
			try{
				connection.setAutoCommit(false);
				statement.executeQuery(sqlSelect1);
				statement.executeQuery(sqlSelect2);
				statement.executeQuery(sqlSelect3);
				statement.executeQuery(sqlSelect4);
				connection.commit();
				System.out.println("Added new inventory  Executed");
				}catch (Exception e){
				connection.rollback();
				System.out.println("Product not added to inventory");
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