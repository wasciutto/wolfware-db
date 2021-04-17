

import java.sql.*;
import java.util.Scanner;

public class getMerchandiseStockReport {


// Update your user info alone here
private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri"; // Using SERVICE_NAME

// Update your user and password info here!

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
            String storeID = null;
            int year = 2020;
            String sqlSelect = null;
			      Scanner sc= new Scanner(System.in);
            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            // Create a statement instance that will be sending
            // your SQL statements to the DBMS
            
      			try{
            System.out.print("Enter store ID: "); 
      			storeID = sc.nextLine();
      			
      			            
            String sql = "SELECT B.STOREID, A.PRODUCTID, A.QUANTITYINSTOCK FROM PRODUCTINVENTORY A JOIN HASPRODUCTINVENTORY B ON A.PRODUCTID = B.PRODUCTID AND A.BATCHID = B.BATCHID WHERE B.STOREID = %s";
			
			sqlSelect = String.format(sql, "'"+storeID+"'");
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for Store Id");
            }
			
			
    			result = statement.executeQuery(sqlSelect);
          if(result.next()) {
              result.beforeFirst();
        			System.out.println("******************************************************************");
        			System.out.println("| STOREID\t|\tPRODUCTID\t|\tQUANTITYINSTOCK  |");
        			System.out.println("******************************************************************");
              while (result.next()) {
                  String STOREID = result.getString("STOREID");
                  String PRODUCTID = result.getString("PRODUCTID");
                  int QUANTITYINSTOCK = result.getInt("QUANTITYINSTOCK");
                  System.out.println("| "+STOREID + "\t\t|\t" + PRODUCTID+ "\t\t|\t" + QUANTITYINSTOCK + "\t\t |");
                  }
    			    System.out.println("******************************************************************");
			    }
         else{
          System.out.println("No results!");
          }
               
  			if(!statement.execute(sqlSelect)) {
  			System.out.println("Incorrect storeID");
  		  return;
  			}
								
			
            } finally {
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
