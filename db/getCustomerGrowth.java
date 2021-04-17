
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class getCustomerGrowth {



private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri"; 


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
            String compareBy = null;
            String defaultCompareBy = "Month";
			      Scanner sc= new Scanner(System.in);
            try {
            
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            
            
      			try{
  				    System.out.print("Enter store ID:"); 
        			storeID = sc.nextLine();
        			
        			System.out.print("Enter Start Date (in YYYY-MM-DD format): ");
        			String startDate = sc.nextLine();
        			String startMonth = startDate.substring(5,7);
  				    String startYear = startDate.substring(0,4);
  				
  				    System.out.print("Enter End Date (in YYYY-MM-DD format): ");
        			String endDate = sc.nextLine();
  				    String endMonth = endDate.substring(5,7);
  				    String endYear = endDate.substring(0,4);
  				
  								
  				    System.out.print("Compare By (Month,Year?): ");
        			compareBy = sc.nextLine();	
			
			        if (compareBy.equals("Month")){
            
                  String sql = "SELECT D.STOREID, YEAR(B.ACTIONDATE) AS YEAR, MONTH(B.ACTIONDATE) AS MONTH, COUNT(A.CUSTOMERID) AS TOTALSIGNUPS FROM CLUBMEMBERS A JOIN SIGNSUPCANCELS B ON A.CUSTOMERID = B.CUSTOMERID JOIN STAFF C ON B.STAFFID = C.STAFFID JOIN STORE D ON C.STOREID = D.STOREID WHERE D.STOREID = %S AND A.ACTIVESTATUS = 'True' AND B.ACTIONDATE >= %s AND B.ACTIONDATE<= %s GROUP BY YEAR,MONTH";
			            sqlSelect = String.format(sql, "'"+storeID+"'", "'"+startDate+"'","'"+endDate+"'");
			          }
			      else {
      
				        String sql = "SELECT D.STOREID, YEAR(B.ACTIONDATE) AS YEAR, MONTH(B.ACTIONDATE) AS MONTH, COUNT(A.CUSTOMERID) AS TOTALSIGNUPS FROM CLUBMEMBERS A JOIN SIGNSUPCANCELS B ON A.CUSTOMERID = B.CUSTOMERID JOIN STAFF C ON B.STAFFID = C.STAFFID JOIN STORE D ON C.STOREID = D.STOREID WHERE D.STOREID = %S AND A.ACTIVESTATUS = 'True' AND B.ACTIONDATE >= %s AND B.ACTIONDATE<= %s GROUP BY YEAR";
				        sqlSelect = String.format(sql, "'"+storeID+"'", "'"+startDate+"'","'"+endDate+"'");
                defaultCompareBy = "Year";
			        }
			
			
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for Store Id or date.");
            }
			
  			try{
            result = statement.executeQuery(sqlSelect);
    			  if(result.next()) {
            
            result.beforeFirst();
            System.out.println("**************************************************************************");
      		  System.out.println("| STOREID\t|\tYEAR\t|\tMONTH\t|\tTOTALSIGNUPS\t |");
      		 
            System.out.println("**************************************************************************");
                while (result.next()) {
                    String STOREID = result.getString("STOREID");
      			        int TOTALSIGNUPS = result.getInt("TOTALSIGNUPS");
      			  
                    String MONTH = result.getString("MONTH");
        			      String YEAR = result.getString("YEAR");
                    System.out.println("| "+STOREID + "\t\t|\t" + YEAR + "\t|\t" + MONTH + "\t|\t" + TOTALSIGNUPS+"\t\t |");
      				  }
                System.out.println("**************************************************************************");
            }
            
           else{
            System.out.println("No results!");
            }
         }
        catch (Exception e){
        System.out.println("Error getting results");
        
        }
			
			if(!statement.execute(sqlSelect)) {
			System.out.println("Incorrect storeID or Year");
			
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
