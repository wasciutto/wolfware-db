
import java.sql.*;
import java.util.Scanner;

public class gettotalSales {


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
      			
      			System.out.print("Enter Reporting Year: ");
      			String input = sc.nextLine();
      			year = Integer.parseInt(input);
            
            String sql = "SELECT D.STOREID,A.PRODUCTID,SUM(A.QUANTITYSOLD) AS QUANTITYSOLD,SUM(A.TOTALSOLDPRICE) AS TOTALSOLDPRICE, YEAR(B.PURCHASEDATE) REPORTINGYEAR FROM TRANSACTIONITEMS A JOIN TRANSACTIONS B ON A.TRANSACTIONID = B.TRANSACTIONID JOIN STAFF C ON B.STAFFID = C.STAFFID JOIN STORE D ON C.STOREID = D.STOREID WHERE D.STOREID = %s AND YEAR(B.PURCHASEDATE)=%d GROUP BY D.STOREID,A.PRODUCTID";
			
			      sqlSelect = String.format(sql, "'"+storeID+"'", year);
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for Store Id or year.");
            }
			
			
         result = statement.executeQuery(sqlSelect);
        if(result.next()) {
          result.beforeFirst();
          System.out.println("*******************************************************************************************************************");
          System.out.println("| STOREID\t|\tPRODUCTID\t|\tQUANTITYSOLD\t|\tTOTALSOLDPRICE\t|\tREPORTINGYEAR\t |");
          System.out.println("*******************************************************************************************************************");
          while (result.next()) {
              String STOREID = result.getString("STOREID");
              String PRODUCTID = result.getString("PRODUCTID");
              String QUANTITYSOLD = result.getString("QUANTITYSOLD");
              float TOTALSOLDPRICE = result.getFloat("TOTALSOLDPRICE");
              int REPORTINGYEAR = result.getInt("REPORTINGYEAR");
              
              System.out.println(STOREID + "\t\t|\t" + PRODUCTID+ "\t\t|\t" + QUANTITYSOLD+ "\t\t|\t" + TOTALSOLDPRICE+ "\t\t|\t"+REPORTINGYEAR + "\t\t |");
          }
        System.out.println("*******************************************************************************************************************");
        }
        else{
          System.out.println("No results!");
          }
		
			if(!statement.execute(sqlSelect)) {
			System.out.println("Incorrect storeID or Year");
			//DBManager.rollbackTransaction();
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
