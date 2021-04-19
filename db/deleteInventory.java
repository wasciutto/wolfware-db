//importing all packages
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteInventory {


// adding database url
private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri"; 
//entering database credentials
private static final String user = "upnadupa";
private static final String password = "Momos";

public static void main(String[] args) {
try {
//registering the jdbc driver.
	Class.forName("org.mariadb.jdbc.Driver");
			//creating all variables required.
            Connection connection = null;
            Statement statement = null;
            ResultSet result = null;
            String productID = null;
			String batchID = null;
			String staffID = null;
			String sqlSelect1 = null;
			String sqlSelect2 = null;
			Scanner sc= new Scanner(System.in);
            
			try {
            //opening a connection and creating a statement to execute queries.
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            
            
      			try{
					//taking required inputs for the queries from command line
					System.out.print("Enter Your Staff ID:"); 
        			staffID = sc.nextLine();
        								
  				    System.out.print("Enter product ID:"); 
        			productID = sc.nextLine();
        			
					System.out.print("Enter batch ID:"); 
        			batchID = sc.nextLine();
					//creating sql statements to be executed and adding the inputs from the command line in the statements.
					String sql = "DELETE FROM PRODUCTINVENTORY WHERE PRODUCTID=%s AND BATCHID = %s";
					sqlSelect1 = String.format(sql,"'"+productID+"'", "'"+batchID+"'");
					
					sql = "INSERT INTO MAINTAINS VALUES('DELETE',%s,%s,%s)";
					sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");
					
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");
            }try{
				
				//setting autocommit to false in case any queris fail.
				connection.setAutoCommit(false);
				
				//executing the queries 
				statement.executeQuery(sqlSelect1);
				statement.executeQuery(sqlSelect2);
				
				//commiting the changes made if and only if both the statements run 
				connection.commit();
				System.out.println("Product removed from inventory. \n");
				
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
					
				//we rollback any changes made to the database  if all the queries weren't executed successfully
				connection.rollback();
				System.out.println("Product removed from added to inventory");
				}	
				System.out.println("*************************************************");
			}finally { 
				
				//closing all resources
				close (result)
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