import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateInventory {

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
                        float price = 0;
                        String staffID = null;
						String transactionID = null;


                        String sqlSelect1 = null;
                        String sqlSelect2 = null;
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
                                price = sc.nextFloat();
                                        sc.nextLine();

                                        System.out.print("Enter batch ID:");
                                batchID = sc.nextLine();

                                System.out.print("Enter Quantity:");
                                quantity = sc.nextInt();
                                        sc.nextLine();

                                        String sql = "UPDATE PRODUCTINVENTORY SET PRODUCTID=%s, BATCHID=%s, PRODUCTNAME=%s, QUANTITYINSTOCK= %s, MARKETPRICE= %s  WHERE PRODUCTID=%s AND BATCHID=%s";
                                        sqlSelect1 = String.format(sql, "'"+productID+"'", "'"+batchID+"'","'"+productName+"'", quantity, price, "'"+productID+"'", "'"+batchID+"'");

                                        sql = "INSERT INTO MAINTAINS VALUES('UPDATE',%s,%s,%s)";
                                        sqlSelect2 = String.format(sql, "'"+staffID+"'", "'"+productID+"'", "'"+batchID+"'");




            }
            catch(Throwable oops) {
              System.out.print("Data inserted was in incorrect format. Please Try again");
                        }
                        try{
                                        connection.setAutoCommit(false);
                                statement.executeUpdate(sqlSelect1);
                                statement.executeUpdate(sqlSelect2);
                                        connection.commit();
                                        System.out.println(" The inventory was update. Have a nice day! :) ");
                                        System.out.println("*************************************************");
								}catch (Exception e){
                                        connection.rollback();
                                        System.out.println(" Sorry! Inventory could not be updated. Try again.");
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
                                                  