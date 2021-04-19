//importing all packages required
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class transferProducts {

	//adding database url
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";
	//loading database credentials
	private static final String user = "pattri";
	private static final String password = "200226336";


public static void main(String[] args) {
try {

//register jdbc driver 
Class.forName("org.mariadb.jdbc.Driver");
			//creating required variables.
            Connection connection = null;
            Statement statement = null;
			ResultSet result = null;
            
			String productID = null;
			String batchID = null;
			String staffID = null;
			String storeID1 = null;
            String storeID2 = null;
			String sqlSelect1 = null;
			String sqlSelect2 = null;
			
			Scanner sc= new Scanner(System.in);
            
			try {
            //opening a connection
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            
            
      			try{
					//taking the required inputs from the command line to execute queries 
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
        			
					//creating sql statements and inserting input values into them 
					String sql = "UPDATE HASPRODUCTINVENTORY SET STOREID=%s  WHERE PRODUCTID=%s AND STOREID=%s AND BATCHID=%s";
					sqlSelect1 = String.format(sql, "'"+storeID2+"'", "'"+productID+"'","'"+storeID1+"'","'"+batchID+"'");
					
					
					sql = "INSERT INTO MAINTAINS VALUES('Transfer',%s,%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
					
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");
            }
			try{
				//setting auto commit to false to prevent any changes made to the database incase of an error.
				connection.setAutoCommit(false);
				//executing the sql queries.
				statement.executeQuery(sqlSelect1);
				statement.executeQuery(sqlSelect2);
				//commiting the changes made to the database once both queries have run. 
				
				connection.commit();
				System.out.println("Product transfer is complete. :) ");
				
				}catch (Exception a){
				//rolling back all the changes made to the database if an error or a failure occurs. 
				connection.rollback();
				System.out.println("Product transfer between stores failed.\n");
				
				try{
					//retrieving the result once the transaction is complete. 
					//slq statement for retrieving entire Product inventory table 
					String sqlresult="SELECT * FROM HASPRODUCTINVENTORY";
					//running the statement to get result. 
					result= statement.executeQuery(sqlresult);
					
					//parsing through the ResultSet one by one and displaying all the values. 
					
					while(result.next())
					{
						String pid= result.getString("PRODUCTID");
						String bid= result.getString("BATCHID");
						String sid= result.getString("STOREID");
						
						System.out.print("Product id: "+pid);
						System.out.print("Batch id: "+bid);
						System.out.print("Store id: "+sid);
					}
					}catch(Exception e){
					System.out.println("error in printing the modified changes");}
				}	
				System.out.println("*************************************************");
			}finally {
				//closing all resources
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