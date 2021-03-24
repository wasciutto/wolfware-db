  
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
private static final String password = "";

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
	statement.executeUpdate("CREATE TABLE SUPPLIERS (SUPPLIERID CHAR(7), SUPPLIERNAME CHAR(150) NOT NULL, EMAILADDRESS VARCHAR(250) NOT NULL, PHONE VARCHAR(15) NOT NULL, LOCATION  VARCHAR(1000) NOT NULL, PRIMARY KEY(SUPPLIERID))");

	// Populate the SUPPLIERS table
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0001', 'ACE Produce', 'aceproduceraleigh@hotmail.com', '9194549898', '23 Farmway Rd')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0002', 'Lee Supply Co', 'leesupply@hotmail.com', '9194542338', '234 Supply Ave')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0003', 'Fresh Farm Eggs','freshfarmeggs@one.com','7778890000','Tootsy NC 27556')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0004', 'First in Line Stationaries','fil@fils.com','1978898000','Apex NC 27506')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0005', 'Bose','bose@out.com','1965598000','Apex NC 27506')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0006', 'Almonds Valley','a.valley@gout.com','1999598000','Ivory California 34456')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0007', 'Fresh Farm Eggs','freshfarmeggs@one.com','7778890000','Tootsy NC 27556')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0008', 'First in line stationaries','fil@fils.com','1978898000','Apex NC 27506')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0009', 'Bose','bose@out.com','1965598000','Apex NC 27506')");
	statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('SUP0010', 'Almonds Valley','a.valley@gout.com','1999598000','Ivory California 34456')");

	// Create STORE
	statement.executeUpdate("CREATE TABLE STORE(	STOREID CHAR(7),MANAGERID CHAR(7),STOREADDRESS VARCHAR(50),PHONENUMBER VARCHAR(11),	PRIMARY KEY (STOREID))");

	//Populate STORE

	statement.executeUpdate("INSERT INTO STORE VALUES ('STR0001','STF0005','Gorman Street,Raleigh','9194321987')");
	statement.executeUpdate("INSERT INTO STORE VALUES ('STR0002','STF0005','Western Blvd.,Raleigh','9192341987')");
	statement.executeUpdate("INSERT INTO STORE VALUES ('STR0003','STF0005','Jackson Street,Raleigh','9194321789')");
	statement.executeUpdate("INSERT INTO STORE VALUES ('STR0004','STF0005','Faucette Dr,Raleigh','9191234987')");
	statement.executeUpdate("INSERT INTO STORE VALUES ('STR0005','STF0005','Clanton Street,Raleigh','9191234567')");

	// Create the HASSUPPLIERS table
	statement.executeUpdate("CREATE TABLE HASSUPPLIERS (STOREID CHAR(7), SUPPLIERID CHAR(7), PRIMARY KEY(SUPPLIERID, STOREID), FOREIGN KEY(SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID) ON UPDATE CASCADE, FOREIGN KEY(STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE)");

	// Populate the HASSUPPLIERS table

	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0001','SUP0001')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0001','SUP0002')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0002','SUP0003')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0002','SUP0004')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0003','SUP0001')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0003','SUP0002')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0003','SUP0004')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0004','SUP0005')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0004','SUP0004')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0004','SUP0006')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0005','SUP0002')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0005','SUP0003')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0005','SUP0004')");
	statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('STR0005','SUP0006')");


	// Create the STAFF table
	statement.executeUpdate("CREATE TABLE STAFF (STAFFID CHAR(7),STOREID CHAR(7),NAME VARCHAR(128),AGE INTEGER,HOMEADDRESS VARCHAR(128),JOBTITLE VARCHAR(64),PHONENUMBER VARCHAR(16),EMAILADDRESS VARCHAR(64),TIMEOFEMPLOYMENT TIMESTAMP,		PRIMARY KEY (STAFFID))");
	// Populate the STAFF table
	statement.executeUpdate("INSERT INTO STAFF VALUES ('STF0001', 'STR0001', 'Luke Skywalker', 24, '100 Desert Ln', 'Warehouse Operator', '9191112222', 'lskywalker@hotmail.com', '2020-09-01T10:19:24')");
	statement.executeUpdate("INSERT INTO STAFF VALUES ('STF0002', 'STR0001', 'Princess Leia', 24, '24 Alderaan Palace', 'Registration Office Operator', '9191112222', 'leiap@hotmail.com', '2015-10-01T10:19:24')");
	statement.executeUpdate("INSERT INTO STAFF VALUES ('STF0003', 'STR0002', 'Han Solo', 31, '783 Millenium Dr', 'Cashier', '9192223333', 'fylingsolo@hotmail.com', '2017-09-04T10:19:24')");
	statement.executeUpdate("INSERT INTO STAFF VALUES ('STF0004', 'STR0002', 'Chewbacca', 64, '783 Millenium Dr', 'Billing Staff', '9193334444', 'walkingcarpet@hotmail.com', '2013-09-01T10:19:24')");
	statement.executeUpdate("INSERT INTO STAFF VALUES ('STF0005', 'STR0003', 'Obi Wan Kenobi', 73, '105 Desert Ln', 'Administrator', '9194445555', 'uncleben@hotmail.com', '2012-09-01T10:19:24')");

	// Create the WORKSIN table
	statement.executeUpdate("CREATE TABLE WORKSIN (STOREID CHAR(7),STAFFID CHAR(7),PRIMARY KEY (STOREID, STAFFID),FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY(STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE)"); 

	// Populate the WORKSIN table

	statement.executeUpdate("INSERT INTO WORKSIN VALUES ('STR0001', 'STF0001')");
	statement.executeUpdate("INSERT INTO WORKSIN VALUES ('STR0001', 'STF0002')");
	statement.executeUpdate("INSERT INTO WORKSIN VALUES ('STR0002', 'STF0003')");
	statement.executeUpdate("INSERT INTO WORKSIN VALUES ('STR0002', 'STF0004')");
	statement.executeUpdate("INSERT INTO WORKSIN VALUES ('STR0003', 'STF0005')");
      
	// Create the PRODUCTINVENTORY table
	statement.executeUpdate("CREATE TABLE PRODUCTINVENTORY (PRODUCTID CHAR(10),BATCHID CHAR(10),QUANTITYINSTOCK INTEGER,PRODUCTNAME VARCHAR(64),MARKETPRICE DECIMAL(8, 2),PRIMARY KEY (PRODUCTID, BATCHID))");

	// Populate the PRODUCTINVENTORY table
	statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000001', 'BTC0000001', 56, 'Organic Apples', 7.99)");
	statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000002', 'BTC0000001', 10, 'Oreo Os', 4.99)");
	statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000003', 'BTC0000001', 15, '16oz Walnuts', 8.99)");
	statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000004', 'BTC0000001', 23, 'Activez Protein Bar 8-Pack', 7.99)");
	statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000005', 'BTC0000001', 19, 'Toliet Paper 12-Pack', 10.99)");
	statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000006', 'BTC0000001', 78, 'Toothpaste', 1.99)");
			
	// Create the SUPPLIEDPRODUCTS table
	statement.executeUpdate("CREATE TABLE SUPPLIEDPRODUCTS (TRANSACTIONID CHAR(12),STOREID CHAR(7),PRODUCTID CHAR(10),BUYQUANTITY INTEGER,SUPPLIEDDATE DATE,PRODUCTIONDATE DATE,EXPIRATIONDATE DATE,BATCHID CHAR(10),PRIMARY KEY (TRANSACTIONID),FOREIGN KEY (STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE,FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID) ON UPDATE CASCADE)");
	// Populate the SUPPLIEDPRODUCTS table
	statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000001', 'STR0001', 'PRD0000001', 100, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000001')");
	statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000002', 'STR0002', 'PRD0000002', 25, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000002')");
	statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000003', 'STR0002', 'PRD0000002', 900, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000002')");
	statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000004', 'STR0003', 'PRD0000003', 45, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000003')");
	statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000005', 'STR0004', 'PRD0000003', 40, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000004')");
	statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000006', 'STR0005', 'PRD0000003', 75, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000005')");
  
	// Create the SUPPLY table
	statement.executeUpdate("CREATE TABLE SUPPLY (SUPPLIERID CHAR(7),	TRANSACTIONID CHAR(12),PRIMARY KEY(SUPPLIERID, TRANSACTIONID),FOREIGN KEY (SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID) ON UPDATE CASCADE,	FOREIGN KEY (TRANSACTIONID) REFERENCES SUPPLIEDPRODUCTS(TRANSACTIONID) ON UPDATE CASCADE)");
	// Populate the SUPPLY table
	statement.executeUpdate("INSERT INTO SUPPLY VALUES ('SUP0001', 'TRN000000001')");
	statement.executeUpdate("INSERT INTO SUPPLY VALUES ('SUP0002', 'TRN000000002')");
	statement.executeUpdate("INSERT INTO SUPPLY VALUES ('SUP0002', 'TRN000000003')");
	statement.executeUpdate("INSERT INTO SUPPLY VALUES ('SUP0003', 'TRN000000004')");
	statement.executeUpdate("INSERT INTO SUPPLY VALUES ('SUP0003', 'TRN000000005')");
	statement.executeUpdate("INSERT INTO SUPPLY VALUES ('SUP0004', 'TRN000000006')");	

      

      // Create the MAINTAINS table
     statement.executeUpdate("CREATE TABLE MAINTAINS (ACTIONTYPE VARCHAR(32),STAFFID CHAR(10),PRODUCTID CHAR(10),FOREIGN KEY (STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID) ON UPDATE CASCADE)");
    statement.executeUpdate("INSERT INTO MAINTAINS VALUES ('Return', 'STF0001', 'PRD0000001')");
    statement.executeUpdate("INSERT INTO MAINTAINS VALUES ('Transfer', 'STF0001', 'PRD0000002')");
    statement.executeUpdate("INSERT INTO MAINTAINS VALUES ('Transfer', 'STF0001', 'PRD0000003')");
    statement.executeUpdate("INSERT INTO MAINTAINS VALUES ('Transfer', 'STF0001', 'PRD0000004')");
    statement.executeUpdate("INSERT INTO MAINTAINS VALUES ('Return', 'STF0001', 'PRD0000005')");
    
    // Create the STOCKS table
    statement.executeUpdate("CREATE TABLE STOCKS (STAFFID CHAR(7),PRODUCTID CHAR(10), BATCHID CHAR(10), TRANSACTIONID CHAR(12),PRIMARY KEY(STAFFID, PRODUCTID, BATCHID, TRANSACTIONID),FOREIGN KEY (STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID) ON UPDATE CASCADE,FOREIGN KEY (TRANSACTIONID) REFERENCES SUPPLIEDPRODUCTS(TRANSACTIONID) ON UPDATE CASCADE)");
    statement.executeUpdate("INSERT INTO STOCKS VALUES ('STF0001', 'PRD0000001',  'BTC0000001', 'TRN000000001')");
    statement.executeUpdate("INSERT INTO STOCKS VALUES ('STF0001', 'PRD0000002', 'BTC0000001', 'TRN000000002')");
    statement.executeUpdate("INSERT INTO STOCKS VALUES ('STF0001', 'PRD0000003', 'BTC0000001', 'TRN000000003')");
    statement.executeUpdate("INSERT INTO STOCKS VALUES ('STF0001', 'PRD0000004', 'BTC0000001', 'TRN000000004')");
    statement.executeUpdate("INSERT INTO STOCKS VALUES ('STF0001', 'PRD0000005', 'BTC0000001', 'TRN000000005')");
    statement.executeUpdate("INSERT INTO STOCKS VALUES ('STF0001', 'PRD0000006', 'BTC0000001', 'TRN000000006')");
    
    // Create the TRANSACTIONS table
    statement.executeUpdate("CREATE TABLE TRANSACTIONS(TRANSACTIONID CHAR(7),CUSTOMERID CHAR(10),STAFFID CHAR(7),PURCHASEDATE DATE NOT NULL,TOTALPRICE FLOAT NOT NULL,PRIMARY KEY (TRANSACTIONID))");
    statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('TRN0001','CUST000001','STF0001', '2020-05-16', '100')");
    statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('TRN0002','CUST000002','STF0002', '2020-05-16', '70')");
    statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('TRN0003','CUST000001','STF0003', '2020-05-16', '5')");
    statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('TRN0004','CUST000003','STF0001', '2020-05-16', '10')");
    statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('TRN0005','CUST000003','STF0002', '2020-05-16', '50')");
    
    // Create the TRANSACTIONITEMS table
    statement.executeUpdate("CREATE TABLE TRANSACTIONITEMS(TRANSACTIONID CHAR(7),TRANSACTIONITEMID CHAR(10),PRODUCTID CHAR(7),QUANTITYSOLD INTEGER,TOTALSOLDPRICE FLOAT,PRIMARY KEY(TRANSACTIONID, TRANSACTIONITEMID),FOREIGN KEY(TRANSACTIONID) REFERENCES TRANSACTIONS(TRANSACTIONID) ON UPDATE CASCADE)");
    statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('TRN0001','TRNITM0001','PRD0001','5','50')");
    statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('TRN0002','TRNITM0002','PRD0002','1','75')");
    statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('TRN0003','TRNITM0003','PRD0003','2','20')");
    statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('TRN0004','TRNITM0004','PRD0004','4','32')");
    statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('TRN0005','TRNITM0005','PRD0005','7','55.55')");
    
    // Create the PERFORMSTRANSACTIONS table
    statement.executeUpdate("CREATE TABLE PERFORMSTRANSACTIONS(STAFFID CHAR(7),TRANSACTIONID CHAR(7),PRIMARY KEY(STAFFID,TRANSACTIONID),FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY (TRANSACTIONID) REFERENCES TRANSACTIONS(TRANSACTIONID) ON UPDATE CASCADE)");
    statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('STF0003','TRN0001')");
    statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('STF0003','TRN0002')");
    statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('STF0003','TRN0003')");
    statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('STF0003','TRN0004')");
    statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('STF0003','TRN0005')");
    
	//create supplier bills table
    statement.executeUpdate("CREATE TABLE SUPPLIERBILLS (BILLID CHAR(7),BILLAMOUNT DECIMAL(8,2),BILLPAID BOOLEAN,BATCHID CHAR(10), PRIMARY KEY(BILLID))");
	
	// Populate the supplier bills table
	
	statement.executeUpdate("INSERT INTO SUPPLIERBILLS VALUES ('BILLS0005', '2430','TRUE', 'BTC0000001')");
	statement.executeUpdate("INSERT INTO SUPPLIERBILLS VALUES ('BILLS0003', '1500','TRUE', 'BTC0000002')");
	statement.executeUpdate("INSERT INTO SUPPLIERBILLS VALUES ('BILLS0005', '2430','TRUE', 'BTC0000002')");
	statement.executeUpdate("INSERT INTO SUPPLIERBILLS VALUES ('BILLS0004', '1580','FALSE','BTC0000003')");
	statement.executeUpdate("INSERT INTO SUPPLIERBILLS VALUES ('BILLS0006', '2430','TRUE', 'BTC0000004')");
	statement.executeUpdate("INSERT INTO SUPPLIERBILLS VALUES ('BILLS0002', '700','FALSE', 'BTC0000005')");
	
	
	statement.executeUpdate("CREATE TABLE CUSTOMERREWARDS (BILLID CHAR(9),BILLAMOUNT DECIMAL(8,2),BILLPAID BOOLEAN, PRIMARY KEY(BILLID))");
	statement.executeUpdate("INSERT INTO CUSTOMERREWARDS VALUES ('BILLC0001', '30','FALSE')");
	statement.executeUpdate("INSERT INTO CUSTOMERREWARDS VALUES ('BILLC0002', '110','TRUE')");
	statement.executeUpdate("INSERT INTO CUSTOMERREWARDS VALUES ('BILLC0003', '45','FALSE')");
	statement.executeUpdate("INSERT INTO CUSTOMERREWARDS VALUES ('BILLC0004', '68','TRUE')");
	statement.executeUpdate("INSERT INTO CUSTOMERREWARDS VALUES ('BILLC0005', '22','FALSE')");


	statement.executeUpdate("CREATE TABLE MANAGESSUPPLIERBILLS (SUPPLIERID CHAR(9),FOREIGN KEY(CUSTOMERID) REFERENCES CLUBMEMBERS(CUSTOMERID) ON UPDATE CASCADE,FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,, PRIMARY KEY(BILLID))");
	statement.executeUpdate("INSERT INTO MANAGESSUPPLIERBILLS VALUES ('BILLS0001','SUP0001','STF0004')");
	statement.executeUpdate("INSERT INTO MANAGESSUPPLIERBILLS VALUES ('BILLS0002','SUP0002','STF0004')");
	statement.executeUpdate("INSERT INTO MANAGESSUPPLIERBILLS VALUES ('BILLS0003','SUP0002','STF0004')");
	statement.executeUpdate("INSERT INTO MANAGESSUPPLIERBILLS VALUES ('BILLS0004','SUP0003','STF0004')");
	statement.executeUpdate("INSERT INTO MANAGESSUPPLIERBILLS VALUES ('BILLS0005','SUP0004','STF0004')");
	

	statement.executeUpdate("CREATE TABLE MANAGESCUSTOMERREWARDS (BILLID CHAR(9), FOREIGN KEY(CUSTOMERID) REFERENCES CLUBMEMBERS(CUSTOMERID) ON UPDATE CASCADE,FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE, PRIMARY KEY(BILLID))");
	statement.executeUpdate("INSERT INTO MANAGESCUSTOMERREWARDS VALUES ('BILLC0001','CUST000004','STF0004');");
	statement.executeUpdate("INSERT INTO MANAGESCUSTOMERREWARDS VALUES ('BILLC0002','CUST000003','STF0004');");
	statement.executeUpdate("INSERT INTO MANAGESCUSTOMERREWARDS VALUES ('BILLC0003','CUST000002','STF0004');");
	statement.executeUpdate("INSERT INTO MANAGESCUSTOMERREWARDS VALUES ('BILLC0004','CUST000001','STF0004');");
	statement.executeUpdate("INSERT INTO MANAGESCUSTOMERREWARDS VALUES ('BILLC0005','CUST000005','STF0004');");


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

