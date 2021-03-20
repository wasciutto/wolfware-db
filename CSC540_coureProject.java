// This example is created by Seokyong Hong
// modified by Shrikanth N C to support MySQL(MariaDB)
// modified by Pankaj
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)

import java.sql.*;

public class v1 {


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

            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);

            // Create a statement instance that will be sending
            // your SQL statements to the DBMS
            statement = connection.createStatement();

            // Create the CLUBMEMBERS table
            statement.executeUpdate("CREATE TABLE CLUBMEMBERS (CUSTOMERID CHAR(10),FIRSTNAME VARCHAR(100),LASTNAME VARCHAR(100) NOT NULL"+
			",MEMBERSHIPLEVEL VARCHAR(50) NOT NULL,EMAILADDRESS VARCHAR(250),PHONENO VARCHAR(15),HOMEADDRESS VARCHAR(1000),ACTIVESTATUS VARCHAR(20) NOT NULL, PRIMARY KEY(CUSTOMERID))");

            // Populate the CLUBMEMBERS table
            statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('CUST000001', 'Ned','Stark', 'Platinum','ned.stark@one.com','911-911-9111','123 Street, Winterfell, Zion','Active')");
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('CUST000002', 'Cersei','Lannister', 'Platinum','cersie.lannister@one.com','901-911-9111','11 Street, Westeros Zion','Inactive')");
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('CUST000003', 'Jon','Snow', 'Platinum','jon.snow@one.com','9009117111','456 Street, Westeros Zion','Active')");
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('CUST000004', 'Sansa','Stark', 'Platinum','sansa.stark@one.com','999-911-9111','123 Street, Winterfell, Zion','Active')");
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('CUST000005', 'Ariya','Stark', 'Platinum','ariya.stark@one.com','888-911-9111','123 Street, Winterfell, Zion','Active')");
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('CUST000006', 'Jamie','Lannister', 'Platinum','ned.stark@one.com','911-911-9111','11 Street, Westeros Zion','Inactive')");
            
            // Create the SIGNSUPCANCELS table
            statement.executeUpdate("CREATE TABLE SIGNSUPCANCELS (STAFFID CHAR(7), CUSTOMERID CHAR(10), ACTIONDATE DATE NOT NULL, ACTION VARCHAR(8) NOT NULL, PRIMARY KEY(STAFFID, CUSTOMERID)"+
            ", FOREIGN KEY(CUSTOMERID) REFERENCES CLUBMEMBERS(CUSTOMERID) ON UPDATE CASCADE)");
            //, FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE)");
            
            // Populate the SIGNSUPCANCELS table
            statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('STF1111', 'CUST000001', '2020-03-25','signup')");
			statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('STF1111', 'CUST000003', '2019-03-15','signup')");
			statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('STF1112', 'CUST000004', '2020-03-15','signup')");
			statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('STF1113', 'CUST000005', '2021-01-17', 'signup')");
			statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('STF1114', 'CUST000002', '2019-03-15','cancel')");
      statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('STF1116', 'CUST000006', '2021-03-15','cancel')");
      
            // Create the SUPPLIERS table
            statement.executeUpdate("CREATE TABLE SUPPLIERS (SUPPLIERID CHAR(7), SUPPLIERNAME CHAR(150), EMAILADDRESS VARCHAR(250), PHONE VARCHAR(15), LOCATION  VARCHAR(1000), PRIMARY KEY(SUPPLIERID))");
            
            // Populate the SUPPLIERS table
            statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0001','Fresh Farm Eggs','freshfarmeggs@one.com','7778890000','Tootsy NC 27556')");
			statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0002','First in line stationaries','fil@fils.com','1978898000','Apex NC 27506')");
			statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0003','Bose','bose@out.com','1965598000','Apex NC 27506')");
			statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0004','Almonds Valley','a.valley@gout.com','1999598000','Ivory California 34456')");
			
            // Create the HASSUPPLIERS table
            statement.executeUpdate("CREATE TABLE HASSUPPLIERS (STOREID CHAR(7), SUPPLIERID CHAR(7), PRIMARY KEY(SUPPLIERID, STOREID)"+
            ",FOREIGN KEY(SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID) ON UPDATE CASCADE)");
            //, FOREIGN KEY(STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE)");
            
            // Populate the SUPPLIERS table
            
            statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0001')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0002')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0003')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0004')");
      statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0020','SUP0002')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0020','SUP0003')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0030','SUP0002')");
      statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0030','SUP0004')");
            
            
            } finally {
                
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
  
}