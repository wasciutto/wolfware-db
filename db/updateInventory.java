import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
<<<<<<< HEAD

public class addNewInventory {
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateInventory {
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8



private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri"; 


private static final String user = "pattri";
private static final String password = "200226336";

public static void main(String[] args) {
try {
<<<<<<< HEAD
// Loading the driver. This creates an instance of the driver
// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.
=======
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8


Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;
<<<<<<< HEAD
            ResultSet result = null;
=======
            
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8
            
			String productName = null;
			String productID = null;
			String batchID = null;
			int quantity = 0;
			int price = 0;
			String staffID = null;
            String transactionID = null;
			
<<<<<<< HEAD
			String sqlSelect = null;
=======
			String sqlSelect1 = null;
			String sqlSelect2 = null;
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8
			Scanner sc= new Scanner(System.in);
            
			try {
            
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            
            
      			try{
					System.out.print("Enter Your Staff ID:"); 
        			staffID = sc.nextLine();
        								
					System.out.print("Enter Product Name:"); 
        			productName = sc.nextLine();
        			
  				    System.out.print("Enter product ID:"); 
        			productID = sc.nextLine();
        			
					System.out.print("Enter Price:"); 
        			price = sc.nextInt();
<<<<<<< HEAD
=======
					sc.nextLine();
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8
					
					System.out.print("Enter batch ID:"); 
        			batchID = sc.nextLine();
					
        			System.out.print("Enter Quantity:"); 
        			quantity = sc.nextInt();
<<<<<<< HEAD
					
=======
					sc.nextLine();
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8
					
					String sql = "UPDATE PRODUCTINVENTORY SET PRODUCTID=%s, BATCHID=%s, QUANTITYINSTOCK= %s, PRODUCTNAME=%s, MARKETPRICE= %s WHERE PRODUCTID=%s AND BATCHID=%s";
					sqlSelect1 = String.format(sql, "'"+productID+"'", "'"+batchID+"'","'"+quantity+"'","'"+productName+"'","'"+price+"'", "'"+productID+"'", "'"+batchID+"'");
					
<<<<<<< HEAD
					try{
						result = statement.executeUpdate(sqlSelect)
					}
					catch(Exception e){
						System.out.println("Product details were not updated. Please enter valid data");
						return;
					}
					
					String sql = "INSERT INTO MAINTAINS VALUES('UPDATE',%s,%s,%s)";
					sqlSelect = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
					try{
						result = statement.executeUpdate(sqlSelect)
					}
					catch(Exception e){
							System.out.println("Warehouse operators action was not logged into the Maintainance registry.");
						return;
					} 
=======
					
					
					sql = "INSERT INTO MAINTAINS VALUES('UPDATE',%s,%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8
					
					
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");
<<<<<<< HEAD
            }
								
			
            } finally {
                close(result);                  
=======
            }try{
				connection.setAutoCommit(false);
				statement.executeQuery(sqlSelect1);
				statement.executeQuery(sqlSelect2);
				connection.commit();
				System.out.println("update inventory  Executed");
				}catch (Exception e){
				connection.rollback();
				System.out.println("Inventory Not updated");
				}	
				System.out.println("*************************************************");
			} finally {
                             
>>>>>>> f4f81e8508d9b2aeb0c39a2dff83977ef60dc6e8
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