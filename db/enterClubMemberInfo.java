import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class enterClubMemberInfo {


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
            
			      String customerID = null;
            String sqlSelect = null;
			      String sqlSelect1 = null;
            String showResultSQL = null;
			      Scanner sc= new Scanner(System.in);
            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();
            // Create a statement instance that will be sending
            // your SQL statements to the DBMS
            
      			try{
            
         // Get User Input
				System.out.print("Enter customer ID: "); 
      			customerID = sc.nextLine();
				
				System.out.print("Enter first name: "); 
      			String fname = sc.nextLine();
				
				System.out.print("Enter last name: "); 
      			String lname = sc.nextLine();
				
				System.out.print("Enter membership level: ");
      			String mLevel = sc.nextLine();
				
				System.out.print("Enter email address: ");
				String email= sc.nextLine();
				
				System.out.print("Enter phone number: ");
				String phoneNumber= sc.nextLine();
				
				System.out.print("Enter home address: ");
				String homeAddr= sc.nextLine();
				
				System.out.print("Enter active status: ");
      			String status = sc.nextLine();
				
				System.out.print("Enter staff ID: "); 
      			String staffID = sc.nextLine();
				
				System.out.print("Sign up or Cancel membership? "); 
      			String action = sc.nextLine();
      			
				System.out.print("Enter action date (in YYYY-MM-DD format): "); 
      			String actionD = sc.nextLine();
				
      	
        // Format SQL statements
        String sql= "INSERT INTO CLUBMEMBERS VALUES (%s,%s,%s,%s,%s,%s,%s,%s)";
				sqlSelect = String.format(sql,"'"+customerID+"'","'"+fname+"'","'"+lname+"'","'"+mLevel+"'","'"+email+"'","'"+phoneNumber+"'","'"+homeAddr+"'","'"+status+"'");
				String sql1= "INSERT INTO SIGNSUPCANCELS VALUES (%s,%s,%s,%s)";
				sqlSelect1 = String.format(sql1,"'"+staffID+"'","'"+customerID+"'","'"+actionD+"'","'"+action+"'");
			  showResultSQL = "SELECT CUSTOMERID,FIRSTNAME,LASTNAME,MEMBERSHIPLEVEL,EMAILADDRESS,PHONENO as PHONENUMBER, HOMEADDRESS,ACTIVESTATUS FROM CLUBMEMBERS";
			
            }
            catch(Throwable oops) {
              System.out.print("Incorrect format for input");
            }
			
			try{
			connection.setAutoCommit(false);
  			statement.executeQuery(sqlSelect);
			statement.executeQuery(sqlSelect1);
			connection.commit();
			System.out.println("Statement executed successfully");
      System.out.println("View updated results? Yes/No?");
      String decision = sc.nextLine();
      if (decision.equals("Yes")) 
      {
          
          try{
            result = statement.executeQuery(showResultSQL);
    			  if(result.next()) {
            
            result.beforeFirst();
            System.out.println("**************************************************************************************************************************************");
      		  System.out.println("| CUSTOMERID\t|FIRSTNAME\t|LASTNAME\t|MEMBERSHIPLEVEL\t\t|EMAILADDRESS\t\t|PHONENUMBER\t|HOMEADDRESS\t\t|ACTIVESTATUS |");
      		 
            System.out.println("**************************************************************************************************************************************");
                while (result.next()) {
                    String CUSTOMERID = result.getString("CUSTOMERID");
                    String FIRSTNAME = result.getString("FIRSTNAME");
                    String LASTNAME = result.getString("LASTNAME");
                    String MEMBERSHIPLEVEL = result.getString("MEMBERSHIPLEVEL");
                    String EMAILADDRESS = result.getString("EMAILADDRESS");
                    String PHONENUMBER = result.getString("PHONENUMBER");
                    String HOMEADDRESS = result.getString("HOMEADDRESS");
                    String ACTIVESTATUS = result.getString("ACTIVESTATUS");
      			        
                    System.out.println("| "+CUSTOMERID + "\t\t|" + FIRSTNAME + "\t\t|" + LASTNAME + "\t\t|" + MEMBERSHIPLEVEL+"\t\t\t|" + EMAILADDRESS + "\t\t|" +PHONENUMBER+ "\t\t|" +HOMEADDRESS+ "\t\t|" +ACTIVESTATUS+" |");
      				  }
                System.out.println("**************************************************************************************************************************************");
            }
            
           else{
            System.out.println("No results!");
            }
         }
        catch (Exception e){
        System.out.println("Error getting results");
        
        }
          
          
      }
      else {
      }
          }
         catch (Exception e){
         System.out.println(e);
		    connection.rollback();
        System.out.println("Statement not executed");
        
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