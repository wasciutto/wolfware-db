import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.lang.String;

public class addNewInventory {



private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/upnadupa";


private static final String user = "upnadupa";
private static final String password = "Momos";

public static void main(String[] args) {
try {


Class.forName("org.mariadb.jdbc.Driver");

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

            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();


			try{
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
									sqlcount = "SELECT * FROM SUPPLIEDPRODUCTS";
									try{
									result = statement.executeQuery(sqlcount);
									result.last();
									rows = result.getRow();
									}catch(Exception e){
											System.out.println("\n Transaction data could not be loaded. Please try again.");
									}
									String zeroDigitString = String.format("%0"+(9-String.valueOf(rows+1).length())+"d", 0);
									transactionID = "TRN" + zeroDigitString + String.valueOf(rows+1);

							}catch(Exception e){
									System.out.println("Transaction Number could not be generated. Please try again. ");
							}

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
					connection.setAutoCommit(false);
					System.out.println(sqlSelect1);
					System.out.println(sqlSelect2);
					System.out.println(sqlSelect3);
					System.out.println(sqlSelect4);
					connection.commit();
					System.out.println("Added new inventory  Executed");
					System.out.println("*************************************************");
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