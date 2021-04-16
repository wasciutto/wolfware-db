import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class getCustomerPurchaseHistory {


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
            String customerID = null;
            
            int year = 2020;
            String sqlSelect = null;
            String compareBy = null;
			      Scanner sc= new Scanner(System.in);
            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            // Create a statement instance that will be send
            // SQL statements to the DBMS
            
      			try{
				System.out.print("Enter store ID: "); 
      			storeID = sc.nextLine();
				
				System.out.print("Enter customer ID: "); 
      			customerID = sc.nextLine();
      			
      			System.out.print("Enter Start Date (in YYYY-MM-DD format in quotes): ");
      			String startDate = sc.nextLine();
      			String startMonth = startDate.substring(5,7);
				String startYear = startDate.substring(0,4);
				
				System.out.print("Enter End Date (in YYYY-MM-DD format in quotes): ");
      			String endDate = sc.nextLine();
				String endMonth = endDate.substring(5,7);
				String endYear = endDate.substring(0,4);
				
			String sql = "SELECT C.STOREID, A.CUSTOMERID, B.PRODUCTID, A.PURCHASEDATE, B.QUANTITYSOLD FROM TRANSACTIONS A, TRANSACTIONITEMS B,STAFF C WHERE A.TRANSACTIONID = B.TRANSACTIONID AND A.STAFFID = C.STAFFID AND A.CUSTOMERID = %s AND A.PURCHASEDATE >=%s AND A.PURCHASEDATE <=%s AND C.STOREID = %s GROUP BY B.PRODUCTID, A.PURCHASEDATE";
			sqlSelect = String.format(sql, "'"+customerID+"'","'"+startDate+"'","'"+endDate+"'","'"+storeID+"'");	
			
			
			
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for Store Id or year.");
            }
			
			try{
  			  result = statement.executeQuery(sqlSelect);
          System.out.println("******************************************************************************************************************");
		  
          System.out.println("| STOREID\t|\tCUSTOMERID\t|\tPRODUCTID\t|\tPURCHASEDATE\t|\tQUANTITYSOLD\t |");
		  
		  
          System.out.println("******************************************************************************************************************");
          while (result.next()) {
              String STOREID = result.getString("STOREID");
              String CUSTOMERID = result.getString("CUSTOMERID");
              String PRODUCTID = result.getString("PRODUCTID");
              String PURCHASEDATE = result.getString("PURCHASEDATE");
			        int QUANTITYSOLD = result.getInt("QUANTITYSOLD");
			  
			        System.out.println("| "+STOREID + "\t|\t" + CUSTOMERID+ "\t|\t" + PRODUCTID + "\t\t|\t" + PURCHASEDATE+ "\t|\t" + QUANTITYSOLD +"\t\t |");
			  }
          System.out.println("******************************************************************************************************************");
        }
        catch (Exception e){
        System.out.println("No results");
        
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
