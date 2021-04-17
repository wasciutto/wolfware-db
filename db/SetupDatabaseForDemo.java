import java.sql.*;

/**
	Setups up the Wolfware Warehouse Database and populates with sample data.
*/
public class SetupDatabaseForDemo {
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

	private static final String user = "pattri";
	private static final String password = "200226336";

	public static void main(String[] args) {
		Connection connection = null;
    
    try {
			// Loading the driver. This creates an instance of the driver
			// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.
			Class.forName("org.mariadb.jdbc.Driver");
			
			// Get a connection instance from the first driver in the
			// DriverManager list that recognizes the URL jdbcURL
			connection = DriverManager.getConnection(jdbcURL, user, password);

      //clearDatabase(connection);
			createTables(connection);			
			
		} catch(Throwable oops) {
				oops.printStackTrace();
		} finally {
			close(connection);
		}
	}
	
	private static void createTables(Connection connection) throws SQLException {
		Statement statement = null;
    
    try {
			statement = connection.createStatement();
				
			// Create the CLUBMEMBERS table
			statement.executeUpdate("CREATE TABLE CLUBMEMBERS (CUSTOMERID CHAR(10),FIRSTNAME VARCHAR(100),LASTNAME VARCHAR(100) NOT NULL"+
			",MEMBERSHIPLEVEL VARCHAR(50) NOT NULL,EMAILADDRESS VARCHAR(250),PHONENO VARCHAR(15),HOMEADDRESS VARCHAR(1000),ACTIVESTATUS VARCHAR(20) NOT NULL, PRIMARY KEY(CUSTOMERID))");

			// Populate the CLUBMEMBERS table
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('5001', 'James','Smith', 'Gold','James5001@gmail.com','919-5555-123','5500, E Street, NC','True')");
			statement.executeUpdate("INSERT INTO CLUBMEMBERS VALUES ('5002', 'David','Smith', 'Platinum ','David5002@gmail.com','919-5555-456','5501 F Street, NC','True')");
			
			// Create the SIGNSUPCANCELS table  
			statement.executeUpdate("CREATE TABLE SIGNSUPCANCELS (STAFFID CHAR(7), CUSTOMERID CHAR(10), ACTIONDATE DATE NOT NULL, ACTION VARCHAR(8) NOT NULL, PRIMARY KEY(STAFFID, CUSTOMERID)"+
			", FOREIGN KEY(CUSTOMERID) REFERENCES CLUBMEMBERS(CUSTOMERID) ON UPDATE CASCADE)");
			//, FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE)");
			
			// Populate the SIGNSUPCANCELS table  
			statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('1003', '5001', '2019-08-01','signup')");
			statement.executeUpdate("INSERT INTO SIGNSUPCANCELS VALUES ('1003', '5002', '2018-01-01','signup')");
					
			// Create the SUPPLIERS table
			statement.executeUpdate("CREATE TABLE SUPPLIERS (SUPPLIERID CHAR(7), SUPPLIERNAME CHAR(150) NOT NULL, PHONE VARCHAR(15) NOT NULL, EMAILADDRESS VARCHAR(250) NOT NULL, LOCATION  VARCHAR(1000) NOT NULL, PRIMARY KEY(SUPPLIERID))");

			// Populate the SUPPLIERS table
			statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('4001', 'A Food Wholesale', '919-4444-123', 'afood@gmail.com', '4401, A Street, NC')");
			statement.executeUpdate("INSERT INTO SUPPLIERS VALUES ('4002', 'US Foods', '919-4444-456', 'usfoods@gmail.com', '4402, G Street, NC')");
			
			// Create STORE
			statement.executeUpdate("CREATE TABLE STORE(STOREID CHAR(7),MANAGERID CHAR(7),STOREADDRESS VARCHAR(50),PHONENUMBER VARCHAR(15),	PRIMARY KEY (STOREID))");

			//Populate STORE

			statement.executeUpdate("INSERT INTO STORE VALUES ('2001','1001','2221, B Street, NC','919-2222-123')");
			statement.executeUpdate("INSERT INTO STORE VALUES ('2002','1002','2222, C Street, NC','919-2222-456')");
			

			// Create the HASSUPPLIERS table  
			statement.executeUpdate("CREATE TABLE HASSUPPLIERS (STOREID CHAR(7), SUPPLIERID CHAR(7), PRIMARY KEY(SUPPLIERID, STOREID), FOREIGN KEY(SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID) ON UPDATE CASCADE, FOREIGN KEY(STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE)");

			// Populate the HASSUPPLIERS table  
					
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('2001','4001')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('2001','4002')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('2002','4001')");
			statement.executeUpdate("INSERT INTO HASSUPPLIERS VALUES ('2002','4002')");
			
					
					
			// Create the STAFF table
			statement.executeUpdate("CREATE TABLE STAFF (STAFFID CHAR(7),STOREID CHAR(7),NAME VARCHAR(128),AGE INTEGER,HOMEADDRESS VARCHAR(128),JOBTITLE VARCHAR(64),PHONENUMBER VARCHAR(16),EMAILADDRESS VARCHAR(64),TIMEOFEMPLOYMENT DATE,		PRIMARY KEY (STAFFID))");
			// Populate the STAFF table
			statement.executeUpdate("INSERT INTO STAFF VALUES ('1001', '2001', 'John', 32, '1101, S Street, NC', 'Manager', '919-1111-123', 'john01@gmail.com', '2018-10-10')");
			statement.executeUpdate("INSERT INTO STAFF VALUES ('1002', '2002', 'Alex', 42, '1102, T Street, NC', 'Manager', '919-1111-456', 'alex12@gmail.com', '2015-07-19')");
			statement.executeUpdate("INSERT INTO STAFF VALUES ('1003', '2001', 'Mary', 28, '1103, U Street, NC', 'cashier', '919-1111-789', 'mary34@gmail.com', '2019-07-19')");
			
			// Create the WORKSIN table
			statement.executeUpdate("CREATE TABLE WORKSIN (STOREID CHAR(7),STAFFID CHAR(7),PRIMARY KEY (STOREID, STAFFID),FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY(STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE)"); 

			// Populate the WORKSIN table

			statement.executeUpdate("INSERT INTO WORKSIN VALUES ('2001', '1001')");
			statement.executeUpdate("INSERT INTO WORKSIN VALUES ('2002', '1002')");
			statement.executeUpdate("INSERT INTO WORKSIN VALUES ('2001', '1003')");
			
			  
			// Create the PRODUCTINVENTORY table
			statement.executeUpdate("CREATE TABLE PRODUCTINVENTORY (PRODUCTID CHAR(10),BATCHID CHAR(10),PRODUCTNAME VARCHAR(64),QUANTITYINSTOCK INTEGER,MARKETPRICE DECIMAL(8, 2),PRIMARY KEY (PRODUCTID, BATCHID))");

			// Populate the PRODUCTINVENTORY table
			statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('3001', 'BTC001', 'AAA Paper Towels',100, 20)");
			statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('3002', 'BTC002', 'BBB Hand soap',200, 10)");
			statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('3001', 'BTC003', 'AAA Paper Towels',150, 20)");
			statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('3002', 'BTC004', 'BBB Hand soap',0, 10)");
			statement.executeUpdate("INSERT INTO PRODUCTINVENTORY VALUES ('3003', 'BTC005', 'CCC Red Wine',100, 30)");
			
				
			// Create the HASPRODUCTINVENTORY table
			statement.executeUpdate("CREATE TABLE HASPRODUCTINVENTORY (PRODUCTID CHAR(10),BATCHID CHAR(10),STOREID CHAR(7),PRIMARY KEY (PRODUCTID, BATCHID, STOREID), FOREIGN KEY (STOREID) REFERENCES STORE(STOREID) ON UPDATE CASCADE, FOREIGN KEY (PRODUCTID,BATCHID) REFERENCES PRODUCTINVENTORY(PRODUCTID,BATCHID) ON UPDATE CASCADE)");

			statement.executeUpdate("INSERT INTO HASPRODUCTINVENTORY VALUES ('3001', 'BTC001', '2001')");
			statement.executeUpdate("INSERT INTO HASPRODUCTINVENTORY VALUES ('3002', 'BTC002', '2001')");
			statement.executeUpdate("INSERT INTO HASPRODUCTINVENTORY VALUES ('3001', 'BTC003', '2002')");
			statement.executeUpdate("INSERT INTO HASPRODUCTINVENTORY VALUES ('3002', 'BTC004', '2002')");
			statement.executeUpdate("INSERT INTO HASPRODUCTINVENTORY VALUES ('3003', 'BTC005', '2001')");
			
			  
			// Create DISCOUNT table
			statement.executeUpdate("CREATE TABLE DISCOUNT (DISCOUNTID CHAR(7), DISCOUNTTYPE VARCHAR(32), VALUE DECIMAL(8,2), STARTDATE DATE, ENDDATE DATE, PRIMARY KEY(DISCOUNTID))");
			
			statement.executeUpdate("INSERT INTO DISCOUNT VALUES ('7001', 'percent off', 20, '2020-01-01', '2021-05-01')");
			statement.executeUpdate("INSERT INTO DISCOUNT VALUES ('7002', 'percent off', 20, '2020-01-01', '2021-05-01')");
			
			  
			// Create HASDISCOUNT table
			statement.executeUpdate("CREATE TABLE HASDISCOUNT (DISCOUNTID CHAR(7), PRODUCTID CHAR(10), BATCHID CHAR(10), PRIMARY KEY(DISCOUNTID, PRODUCTID, BATCHID), FOREIGN KEY(DISCOUNTID) REFERENCES DISCOUNT(DISCOUNTID) ON UPDATE CASCADE, FOREIGN KEY (PRODUCTID,BATCHID) REFERENCES PRODUCTINVENTORY(PRODUCTID,BATCHID) ON UPDATE CASCADE)");
			
			statement.executeUpdate("INSERT INTO HASDISCOUNT VALUES ('7001', '3001', 'BTC001')");
			statement.executeUpdate("INSERT INTO HASDISCOUNT VALUES ('7001', '3001', 'BTC003')");
			statement.executeUpdate("INSERT INTO HASDISCOUNT VALUES ('7002', '3003', 'BTC005')");
			
			
			  
			// Create the SUPPLIEDPRODUCTS table  
			statement.executeUpdate("CREATE TABLE SUPPLIEDPRODUCTS (TRANSACTIONID CHAR(12),STOREID CHAR(7),PRODUCTID CHAR(10),BUYQUANTITY INTEGER,SUPPLIEDDATE DATE,PRODUCTIONDATE DATE,EXPIRATIONDATE DATE,BATCHID CHAR(10),BUYPRICE DECIMAL(8, 2),PRIMARY KEY (TRANSACTIONID))");
			// Populate the SUPPLIEDPRODUCTS table   
			statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000001', '2001', '3001', 100, '2020-05-05', '2020-01-01', '2025-01-01', 'BTC001','10')");
			statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000002', '2001', '3002', 200, '2020-05-05', '2020-01-01', '2022-01-01', 'BTC002','5')");
			statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000003', '2002', '3001', 150, '2020-05-05', '2020-01-01', '2025-01-01', 'BTC003','10')");
			statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000004', '2002', '3002', 100, '2020-05-05', '2020-01-01', '2022-01-01', 'BTC004','5')");
			statement.executeUpdate("INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000005', '2001', '3003', 100, '2021-05-05', '2021-01-01', '2022-01-01', 'BTC005','15')");
			 
		  
			// Create the SUPPLY table  
			statement.executeUpdate("CREATE TABLE SUPPLY (SUPPLIERID CHAR(7),	TRANSACTIONID CHAR(12),PRIMARY KEY(SUPPLIERID, TRANSACTIONID),FOREIGN KEY (SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID) ON UPDATE CASCADE,	FOREIGN KEY (TRANSACTIONID) REFERENCES SUPPLIEDPRODUCTS(TRANSACTIONID) ON UPDATE CASCADE)");
			// Populate the SUPPLY table 
			statement.executeUpdate("INSERT INTO SUPPLY VALUES ('4001', 'TRN000000001')");
			statement.executeUpdate("INSERT INTO SUPPLY VALUES ('4002', 'TRN000000002')");
			statement.executeUpdate("INSERT INTO SUPPLY VALUES ('4001', 'TRN000000003')");
			statement.executeUpdate("INSERT INTO SUPPLY VALUES ('4002', 'TRN000000004')");
			statement.executeUpdate("INSERT INTO SUPPLY VALUES ('4002', 'TRN000000005')");
			
			  
			  

			// Create the MAINTAINS table
			statement.executeUpdate("CREATE TABLE MAINTAINS (ACTIONTYPE VARCHAR(32),STAFFID CHAR(10),PRODUCTID CHAR(10), BATCHID CHAR(10),PRIMARY KEY (STAFFID,PRODUCTID,BATCHID),FOREIGN KEY (STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID) ON UPDATE CASCADE)");
			
			// NO RECORDS SEPCIFIED FOR TRANSFER/RETURN in DEMO DATA
			//statement.executeUpdate("INSERT INTO MAINTAINS VALUES ('Return', '1001', '3001','BTC001')");
			
			
			
			// Create the STOCKS table 
			statement.executeUpdate("CREATE TABLE STOCKS (STAFFID CHAR(7),PRODUCTID CHAR(10), BATCHID CHAR(10), TRANSACTIONID CHAR(12),PRIMARY KEY(STAFFID, PRODUCTID, BATCHID, TRANSACTIONID),FOREIGN KEY (STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID) ON UPDATE CASCADE,FOREIGN KEY (TRANSACTIONID) REFERENCES SUPPLIEDPRODUCTS(TRANSACTIONID) ON UPDATE CASCADE)");
			
			statement.executeUpdate("INSERT INTO STOCKS VALUES ('1001', '3001',  'BTC001', 'TRN000000001')");
			statement.executeUpdate("INSERT INTO STOCKS VALUES ('1001', '3002', 'BTC002', 'TRN000000002')");
			statement.executeUpdate("INSERT INTO STOCKS VALUES ('1002', '3001', 'BTC003', 'TRN000000003')");
			statement.executeUpdate("INSERT INTO STOCKS VALUES ('1002', '3002', 'BTC004', 'TRN000000004')");
			statement.executeUpdate("INSERT INTO STOCKS VALUES ('1001', '3003', 'BTC005', 'TRN000000005')");
			
			// Create the TRANSACTIONS table
			statement.executeUpdate("CREATE TABLE TRANSACTIONS(TRANSACTIONID CHAR(7),CUSTOMERID CHAR(10),STAFFID CHAR(7),PURCHASEDATE DATE NOT NULL,TOTALPRICE FLOAT NOT NULL,PRIMARY KEY (TRANSACTIONID))");
			
			statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('6001','5002','1003', '2020-05-01', '100')");
			statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('6002','5002','1003', '2020-06-01', '100')");
			statement.executeUpdate("INSERT INTO TRANSACTIONS VALUES ('6003','5001','1003', '2020-07-01', '160')");
			
			// Create the TRANSACTIONITEMS table
			statement.executeUpdate("CREATE TABLE TRANSACTIONITEMS(TRANSACTIONID CHAR(7),TRANSACTIONITEMID CHAR(10),PRODUCTID CHAR(7),QUANTITYSOLD INTEGER,TOTALSOLDPRICE FLOAT,PRIMARY KEY(TRANSACTIONID, TRANSACTIONITEMID),FOREIGN KEY(TRANSACTIONID) REFERENCES TRANSACTIONS(TRANSACTIONID) ON UPDATE CASCADE)");
			
			statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('6001','6001-01','3001','5','80')");
			statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('6001','6001-02','3002','2','20')");
			statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('6002','6002-01','3002','10','100')");
			statement.executeUpdate("INSERT INTO TRANSACTIONITEMS VALUES ('6003','6003-01','3001','10','160')");
			
			// Create the PERFORMSTRANSACTIONS table  
			statement.executeUpdate("CREATE TABLE PERFORMSTRANSACTIONS(STAFFID CHAR(7),TRANSACTIONID CHAR(7),PRIMARY KEY(STAFFID,TRANSACTIONID),FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,FOREIGN KEY (TRANSACTIONID) REFERENCES TRANSACTIONS(TRANSACTIONID) ON UPDATE CASCADE)");
			
			statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('1003','6001')");
			statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('1003','6002')");
			statement.executeUpdate("INSERT INTO PERFORMSTRANSACTIONS VALUES('1003','6003')");
			
			
			// Create the SUPPLIERBILLS table
			statement.executeUpdate("CREATE TABLE SUPPLIERBILLS (BILLID CHAR(7),BILLAMOUNT DECIMAL(8,2),BILLPAID BOOLEAN,BATCHID CHAR(10), PRIMARY KEY(BILLID))");
			
			// Create the CUSTOMERREWARDS table
			statement.executeUpdate("CREATE TABLE CUSTOMERREWARDS (BILLID CHAR(7),BILLAMOUNT DECIMAL(8,2),BILLPAID BOOLEAN, PRIMARY KEY(BILLID))");
			
			// Create the MANAGESSUPPLIERBILLS table
			statement.executeUpdate("CREATE TABLE MANAGESSUPPLIERBILLS (BILLID CHAR(7),SUPPLIERID CHAR(7),STAFFID CHAR(7), PRIMARY KEY(BILLID, SUPPLIERID, STAFFID))");
			
			// Create the MANAGESCUSTOMERREWARDS table
			statement.executeUpdate("CREATE TABLE MANAGESCUSTOMERREWARDS (BILLID CHAR(7),CUSTOMERID CHAR(7),STAFFID CHAR(7), PRIMARY KEY(BILLID, CUSTOMERID, STAFFID))");

		} finally {
			close(statement);
		}
		
	}
 
  private static void clearDatabase(Connection connection) {
    String[] tableNames = new String[] {
		"DISCOUNT",
		"HASDISCOUNT",
		
		"PERFORMSTRANSACTIONS",
		"TRANSACTIONITEMS",
		"TRANSACTIONS",
		
		"SIGNSUPCANCELS",
		"MANAGESCUSTOMERREWARDS",
		"CLUBMEMBERS",
		"CUSTOMERREWARDS",
		
		"SUPPLIERBILLS",
		"MANAGESSUPPLIERBILLS",
		
		"MAINTAINS",
		"STOCKS",
		
		"HASPRODUCTINVENTORY",
		"PRODUCTINVENTORY",
		
		"SUPPLY",
		"SUPPLIEDPRODUCTS",
		"HASSUPPLIERS",
		"SUPPLIERS",
		
		"WORKSIN",
		"STORE",
		"STAFF"
	};
	
	for(String tableName : tableNames) {
		Statement statement = null;
		try {
		  statement = connection.createStatement();
		  statement.executeUpdate(String.format("DROP TABLE %s;", tableName));
		} catch(SQLException e) {
			//e.printStackTrace();
			System.out.println(String.format("%s did not exist to delete.", tableName));
		} finally {
			close(statement);
		}
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

