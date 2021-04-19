import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class addReturnstoInventory {

	//adding the database url
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";
	
//database credetials
	private static final String user = "pattri";
	private static final String password = "200226336";


public static void main(String[] args) {
try {
	//register the jdbc driver
	Class.forName("org.mariadb.jdbc.Driver");
			
            //creating all variables required
			Connection connection = null;
            Statement statement = null;
            ResultSet result = null;
			String productName = null;
			String productID = null;
			String batchID = null;
			int quantity = 0;
			String transactionID = null;
			String suppliedDate = null;
			String productionDate = null;
			String experationDate = null;
			String staffID = null;
			String storeID = null;
            
			String sqlSelect1 = null;
			String sqlSelect2 = null;
			Scanner sc= new Scanner(System.in);
            
			try {
            //setting up a connection and creating a statement
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            
            
      			try{
					//accepting all the values required from the command line
					System.out.print("Enter Your Staff ID:"); 
        			staffID = sc.nextLine();
        			
  				    System.out.print("Enter product ID:"); 
        			productID = sc.nextLine();
        			
					System.out.print("Enter batch ID:"); 
        			batchID = sc.nextLine();
					
        			System.out.print("Enter Quantity:"); 
        			quantity = sc.nextInt();
					sc.nextLine();
					
					//creating a sql statement and inserting the input values into the statement.
					String sql = "UPDATE PRODUCTINVENTORY SET QUANTITYINSTOCK = QUANTITYINSTOCK + %s WHERE PRODUCTID=%s AND BATCHID=%s";
					sqlSelect1 = String.format(sql, quantity, "'"+productID+"'", "'"+batchID+"'");
					
					sql = "INSERT INTO MAINTAINS VALUES('Returns',%s,%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
					
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");
            }try{
				//setting autocommit to false
				connection.setAutoCommit(false);
				//executing both the queries.
				statement.executeQuery(sqlSelect1);
				statement.executeQuery(sqlSelect2);
				//commiting the transaction only if both queries get executed. 
				connection.commit();
				System.out.println("Product returned to inventory. Have a nice day! \n");
				
				try{
					//retrieving the result once the transaction is complete. 
					//slq statement for retrieving entire Product inventory table 
					String sqlresult="SELECT * FROM PRODUCTINVENTORY";
					//running the statement to get result. 
					result= statement.executeQuery(sqlresult);
					
					//parsing through the ResultSet one by one and displaying all the values. 
					
					while(result.next())
					{
						String pid= result.getString("PRODUCTID");
						String bid= result.getString("BATCHID");
						String pname = result.getString("PRODUCTNAME");
						int quan= result.getInt("QUANTITYINSTOCK");
						int price= result.getInt("MARKETPRICE");
						
						System.out.print("Product id: "+pid);
						System.out.print("Batch id: "+bid);
						System.out.print("Product name: "+pname);
						System.out.print("Quantity :"+quan);
						System.out.println("Price :"+price);
					}
					}catch(Exception e){
					System.out.println("error in printing the modified changes");}
				
				}catch (Exception e){
				//rollback the transaction incase both the queries are not executed.  
				connection.rollback();
				System.out.println(" Sorry! Product not returned to inventory. Try again! ");
				}	
				System.out.println("*************************************************");
			} finally {
                //closing all resources.
				close (result);             
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