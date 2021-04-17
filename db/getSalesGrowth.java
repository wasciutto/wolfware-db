import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class getSalesGrowth {


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
			      Scanner sc= new Scanner(System.in);
            String defaultCompareBy = "Month";
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
        			
        			System.out.print("Enter Start Date (in YYYY-MM-DD format in quotes): ");
        			String startDate = sc.nextLine();
        			String startMonth = startDate.substring(5,7);
  				    String startYear = startDate.substring(0,4);
        			
        			System.out.print("Enter End Date (in YYYY-MM-DD format in quotes): ");
      			  String endDate = sc.nextLine();
      				String endMonth = endDate.substring(5,7);
      				String endYear = endDate.substring(0,4);
				
								
  		        System.out.print("Compare By (Month,Year?): ");
        			String compareBy = sc.nextLine();	
			
  			      if (compareBy.equals("Month")){
              
              String sql = "SELECT D.STOREID, MONTH(B.PURCHASEDATE) AS MONTH,SUM(B.TOTALPRICE) AS TOTALSALES FROM TRANSACTIONS B JOIN STAFF C ON B.STAFFID = C.STAFFID JOIN STORE D ON C.STOREID = D.STOREID WHERE D.STOREID = %s AND B.PURCHASEDATE >= %s AND B.PURCHASEDATE<= %s GROUP BY MONTH";
  			      sqlSelect = String.format(sql, "'"+storeID+"'", "'"+startDate+"'","'"+endDate+"'");
  			      }
    			else {
      
				    String sql = "SELECT D.STOREID, YEAR(B.PURCHASEDATE) AS YEAR,SUM(B.TOTALPRICE) AS TOTALSALES FROM TRANSACTIONS B JOIN STAFF C ON B.STAFFID = C.STAFFID JOIN STORE D ON C.STOREID = D.STOREID WHERE D.STOREID = %s AND B.PURCHASEDATE >= %s AND B.PURCHASEDATE<= %s GROUP BY YEAR";
				      sqlSelect = String.format(sql, "'"+storeID+"'", "'"+startDate+"'","'"+endDate+"'");
              defaultCompareBy = "Year";
			        }
			
			
            }
            catch(Throwable oops) {
              System.out.println("Incorrect format for Store Id or year.");
            }
			
			try{
  			  result = statement.executeQuery(sqlSelect);
          
          if(result.next()) {
              
              if (defaultCompareBy.equals("Month")) {
                result.beforeFirst();
                System.out.println("**********************************************************");
                System.out.println("| STOREID\t|\tMONTH\t|\tTOTALSALES\t |");
                System.out.println("**********************************************************");
                
                
                while (result.next()) {
                    String STOREID = result.getString("STOREID");
                    String MONTH = result.getString("MONTH");
                  
                    float TOTALSALES = result.getFloat("TOTALSALES");
                    
                    System.out.println("| "+STOREID + "\t\t|\t" + MONTH+ "\t|\t" + TOTALSALES+"\t\t |");
                }
                System.out.println("**********************************************************");
              }
              
              else {
                result.beforeFirst();
                System.out.println("**********************************************************");
                System.out.println("| STOREID\t|\tYEAR\t|\tTOTALSALES\t |");
                System.out.println("**********************************************************");
                
                
                while (result.next()) {
                    String STOREID = result.getString("STOREID");
                    String YEAR = result.getString("YEAR");
                  
                    float TOTALSALES = result.getFloat("TOTALSALES");
                    
                    System.out.println("| "+STOREID + "\t\t|\t" + YEAR+ "\t|\t" + TOTALSALES+"\t\t |");
                }
                System.out.println("**********************************************************");
              
              }
              
            }
          else{
          System.out.println("No results!");
          }
          
          
        }
        catch (Exception e){
        System.out.println("Error getting results");
        
        }
			//Insert the patient into the specified ward
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
