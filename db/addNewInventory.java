import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class addNewInventory {



private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri"; 


private static final String user = "pattri";
private static final String password = "200226336";

public static void main(String[] args) {
try {
// Loading the driver. This creates an instance of the driver
// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.


Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;
            ResultSet result = null;
            
			String productName = null;
			String productID = null;
			String batchID = null;
			int quantity = 0;
			int price = 0;
			String transactionID = null;
			String suppliedDate = null;
			String productionDate = null;
			String experationDate = null;
			String staffID = null;
			String storeID = null;
            
			String sqlSelect1 = null;
			String sqlSelect2 = null;
			String sqlSelect3 = null;
			String sqlSelect4 = null;
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
					
					System.out.print("Enter batch ID:"); 
        			batchID = sc.nextLine();
					
        			System.out.print("Enter Quantity:"); 
        			quantity = sc.nextInt();
					
					System.out.print("Enter Production Date (in YYYY-MM-DD format): ");
        			String productionDate = sc.nextLine();
					
					System.out.print("Enter Supply Date (in YYYY-MM-DD format): ");
        			String suppliedDate = sc.nextLine();
					
  				    System.out.print("Enter Experation Date (in YYYY-MM-DD format): ");
        			String experationDate = sc.nextLine();
					
					System.out.print("Enter Transaction ID:"); 
        			transactionID = sc.nextLine();
					
					System.out.print("Enter Store ID:"); 
        			storeID = sc.nextLine();
        			
					
					String sql = "INSERT INTO PRODUCTINVENTORY VALUES(%s,%s,%s,%s,%s)";
					sqlSelect1 = String.format(sql, "'"+productID+"'", "'"+batchID+"'","'"+quantity+"'","'"+productName+"'","'"+price+"'");
					
					
					String sql = "INSERT INTO MAINTAINS VALUES('STOCKS',%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'");
					 
					
					String sql = "INSERT INTO SUPPLIEDPRODUCTS VALUES(%s,%s,%s,%s,%s,%s,%s,%s)";
					sqlSelect3 = String.format(sql, "'"+transactionID+"'","'"+storeID+"'", "'"+productID+"'","'"+quantity+"'" "'"+suppliedDate+"'", "'"+productionDate+"'", "'"+experationDate+"'", "'"+batchID+"'");
					
					String sql = "INSERT INTO STOCKS VALUES(%s,%s,%s,%s)";
					sqlSelect4 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+transactionID+"'", "'"+batchID+"'");
					
					
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
                close(result);                  
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