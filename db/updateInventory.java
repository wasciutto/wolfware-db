//import required packages
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateInventory {
	//adding database url
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";
	//loading database credentials
	private static final String user = "pattri";
	private static final String password = "200226336";


public static void main(String[] args) {
try {

//register jdbc driver
Class.forName("org.mariadb.jdbc.Driver");
			//creating required variables
            Connection connection = null;
            Statement statement = null;
            ResultSet result = null;

                        String productName = null;
                        String productID = null;
                        String batchID = null;
                        int quantity = 0;
                        float price = 0;
                        String staffID = null;
						String transactionID = null;


                        String sqlSelect1 = null;
                        String sqlSelect2 = null;
                        Scanner sc= new Scanner(System.in);

            
			  try {
			//opening a connection 
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();


                        try{
							//taking inputs from the commandline to execute queries
                                        System.out.print("Enter Your Staff ID:");
                                staffID = sc.nextLine();

                                        System.out.print("Enter Product Name:");
                                productName = sc.nextLine();

                                    System.out.print("Enter product ID:");
                                productID = sc.nextLine();

                                        System.out.print("Enter Price:");
                                price = sc.nextFloat();
                                        sc.nextLine();

                                        System.out.print("Enter batch ID:");
                                batchID = sc.nextLine();

                                System.out.print("Enter Quantity:");
                                quantity = sc.nextInt();
                                        sc.nextLine();
										
										//creating sql statements and inserting the input values into those statements

                                        String sql = "UPDATE PRODUCTINVENTORY SET PRODUCTID=%s, BATCHID=%s, PRODUCTNAME=%s, QUANTITYINSTOCK= %s, MARKETPRICE= %s  WHERE PRODUCTID=%s AND BATCHID=%s";
                                        sqlSelect1 = String.format(sql, "'"+productID+"'", "'"+batchID+"'","'"+productName+"'", quantity, price, "'"+productID+"'", "'"+batchID+"'");

                                        sql = "INSERT INTO MAINTAINS VALUES('UPDATE',%s,%s,%s)";
                                        sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");




            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format. Please Try again");
                        }
                        try{
										//setting auto commit to false to prevent any changes made to the database incase one or more queries fail.
                                        connection.setAutoCommit(false);
										//executing the queries.
										statement.executeUpdate(sqlSelect1);
										statement.executeUpdate(sqlSelect2);
                                        //commiting the changes to the database after the queries have been executed. 
										connection.commit();
                                        System.out.println(" The inventory was update. Have a nice day! :) ");
                                        System.out.println("************************************************* \n ");
										
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
										//incase of an error any changes made to the database are reverted. 
                                        connection.rollback();
                                        System.out.println(" Sorry! Inventory could not be updated. Try again.");
                                }
                                        System.out.println("*************************************************");
                        }finally {
							//closing all resources
							close(result)
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
                                                  