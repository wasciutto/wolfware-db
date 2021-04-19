//importing all requied packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.lang.String;

public class addNewInventory {
	//adding database url
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";
	//loading database credentials
	private static final String user = "pattri";
	private static final String password = "200226336";

public static void main(String[] args) {
try {

//registering the jdbc driver
Class.forName("org.mariadb.jdbc.Driver");
			
			//creating the required variables
            Connection connection = null;
            Statement statement = null;
            ResultSet result = null;

			String productName = null;
			String productID = null;
			String batchID = null;
			int quantity = 0;
			int rows = 0;
			float price = 0;
			float buyPrice = 0;
			String transactionID = null;
			String suppliedDate = null;
			String productionDate = null;
			String experationDate = null;
			String staffID = null;
			String storeID = null;
			String sqlcount = null;
			String sqlSelect1 = null;
			String sqlSelect2 = null;
			String sqlSelect3 = null;
			String sqlSelect4 = null;
			Scanner sc= new Scanner(System.in);

            
			  try {
			//opening a  connection
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
	

			try{
					//taking the required inputs from the command line for executing the queries
					System.out.print("Enter Your Staff ID:");
					staffID = sc.nextLine();

					System.out.print("Enter Product Name:");
					productName = sc.nextLine();

					System.out.print("Enter product ID:");
					productID = sc.nextLine();

					System.out.print("Enter Selling Price:");
					price = sc.nextFloat();
					sc.nextLine();

					System.out.print("Enter Buying Price:");
					buyPrice = sc.nextFloat();
					sc.nextLine();

					System.out.print("Enter batch ID:");
					batchID = sc.nextLine();

					System.out.print("Enter Quantity:");
					quantity = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter Production Date (in YYYY-MM-DD format): ");
					productionDate = sc.nextLine();

					System.out.print("Enter Supply Date (in YYYY-MM-DD format): ");
					suppliedDate = sc.nextLine();

					System.out.print("Enter Experation Date (in YYYY-MM-DD format): ");
					experationDate = sc.nextLine();

					System.out.print("Enter Store ID:");
					storeID = sc.nextLine();
					//generating transaction number
							try{
									//sql query for getting all the data from Supplied Products table
									sqlcount = "SELECT * FROM SUPPLIEDPRODUCTS";
									try{
									//executing the query
									result = statement.executeQuery(sqlcount);
									//going to the last row and checking its row number to generate next  transaction number. 
									result.last();
									rows = result.getRow();
									}catch(Exception e){
											System.out.println("\n Transaction data could not be loaded. Please try again.");
									}
									//calculating the number of zeros to be added to the 'TRN00000000X' format string 
									String zeroDigitString = String.format("%0"+(9-String.valueOf(rows+1).length())+"d", 0);
									//creating the transaction number 
									transactionID = "TRN" + zeroDigitString + String.valueOf(rows+1);

							}catch(Exception e){
									System.out.println("Transaction Number could not be generated. Please try again. ");
							}
							//creating sql statements and inserting the inputs to the sql statements.
							String sql = "INSERT INTO PRODUCTINVENTORY VALUES(%s,%s,%s,%s,%s)";
							sqlSelect1 = String.format(sql, "'"+productID+"'", "'"+batchID+"'",quantity,"'"+productName+"'",price);


							sql = "INSERT INTO MAINTAINS VALUES('STOCKS',%s,%s,%s)";
							sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");


							sql = "INSERT INTO SUPPLIEDPRODUCTS VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s)";
							sqlSelect3 = String.format(sql, "'"+transactionID+"'","'"+storeID+"'", "'"+productID+"'",quantity, "'"+suppliedDate+"'", "'"+productionDate+"'", "'"+experationDate+"'", "'"+batchID+"'",buyPrice);

							sql = "INSERT INTO STOCKS VALUES(%s,%s,%s,%s)";
							sqlSelect4 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+transactionID+"'", "'"+batchID+"'");
							
            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format.");

            }
			try{
					//setting auto commit to false to prevent any changes made to the database incase of an error.
					connection.setAutoCommit(false);
					//executing all the sql statements. 
					statement.executeUpdate(sqlSelect1);
					statement.executeUpdate(sqlSelect2);
					statement.executeUpdate(sqlSelect3);
					statement.executeUpdate(sqlSelect4);
					//commiting the changes made to the database once all the queries have run successfully. 
					connection.commit();
					System.out.println("Product has been added to inventory. Have a nice day! \n");
					System.out.println("*************************************************");
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
					//rolling back any changes made to the database in case of any error. 
					connection.rollback();
					System.out.println("Product not added to inventory");
				}
					System.out.println("*************************************************");
			}finally {
				//closing all resources.
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