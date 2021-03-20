-- General Tables

CREATE TABLE STORE(
	STOREID CHAR(7), 
	MANAGERID CHAR(7), 
	STOREADDRESS VARCHAR(50), 
	PHONENUMBER VARCHAR(11),
	
	CONSTRAINT PK_STOREID PRIMARY KEY (STOREID)
);

INSERT INTO STORE VALUES ('STR0001', 'STF0005', '100 Wilson Ln', '9194542019');
INSERT INTO STORE VALUES ('STR0002', 'STF0005', '24 E Pearson', '9194542019');
INSERT INTO STORE VALUES ('STR0003', 'STF0005', '488 Arlington Ave', '9194322019');
INSERT INTO STORE VALUES ('STR0004', 'STF0005', '45 West St', '9193232019');
INSERT INTO STORE VALUES ('STR0005', 'STF0005', '600 Avington Dr', '91943242019');


CREATE TABLE STAFF (
    STAFFID CHAR(7),
    STOREID CHAR(7),
    NAME VARCHAR(128),
    AGE INTEGER,
    HOMEADDRESS VARCHAR(128),
    JOBTITLE VARCHAR(64),
    PHONENUMBER VARCHAR(16),
    EMAILADDRESS VARCHAR(64),
    TIMEOFEMPLOYMENT TIMESTAMP,
	
	CONSTRAINT PK_STAFFID PRIMARY KEY (STAFFID),
	CONSTRAINT FK_STOREID FOREIGN KEY (STOREID) REFERENCES STORE(STOREID)
);

INSERT INTO STAFF VALUES ('STF0001', 'STR0001', 'Luke Skywalker', 24, '100 Desert Ln', 'Warehouse Operator', '9191112222', 'lskywalker@hotmail.com', '2020-09-01T10:19:24');
INSERT INTO STAFF VALUES ('STF0002', 'STR0001', 'Princess Leia', 24, '24 Alderaan Palace', 'Registration Office Operator', '9191112222', 'leiap@hotmail.com', '2015-10-01T10:19:24');
INSERT INTO STAFF VALUES ('STF0003', 'STR0002', 'Han Solo', 31, '783 Millenium Dr', 'Cashier', '9192223333', 'fylingsolo@hotmail.com', '2017-09-04T10:19:24');
INSERT INTO STAFF VALUES ('STF0004', 'STR0002', 'Chewbacca', 64, '783 Millenium Dr', 'Billing Staff', '9193334444', 'walkingcarpet@hotmail.com', '2013-09-01T10:19:24');
INSERT INTO STAFF VALUES ('STF0005', 'STR0003', 'Obi Wan Kenobi', 73, '105 Desert Ln', 'Administrator', '9194445555', 'uncleben@hotmail.com', '2012-09-01T10:19:24');


-- Warehouse Tables

CREATE TABLE SUPPLIERS (
	SUPPLIERID CHAR(7), 
	SUPPLIERNAME CHAR(150), 
	EMAILADDRESS VARCHAR(250), 
	PHONE VARCHAR(15), 
	LOCATION  VARCHAR(1000),
	
	CONSTRAINT PK_SUPPLIERID PRIMARY KEY (SUPPLIERID)
);

INSERT INTO SUPPLIERS VALUES ('SUP0001', 'ACE Produce', 'aceproduceraleigh@hotmail.com', '9194549898', '23 Farmway Rd');
INSERT INTO SUPPLIERS VALUES ('SUP0002', 'Lee Supply Co', 'leesupply@hotmail.com', '9194542338', '234 Supply Ave');
INSERT INTO SUPPLIERS VALUES ('SUP0003', 'Fresh Farm Eggs','freshfarmeggs@one.com','7778890000','Tootsy NC 27556');
INSERT INTO SUPPLIERS VALUES ('SUP0004', 'First in Line Stationaries','fil@fils.com','1978898000','Apex NC 27506');
INSERT INTO SUPPLIERS VALUES ('SUP0005', 'Bose','bose@out.com','1965598000','Apex NC 27506');
INSERT INTO SUPPLIERS VALUES ('SUP0006', 'Almonds Valley','a.valley@gout.com','1999598000','Ivory California 34456');

CREATE TABLE HASSUPPLIERS (
	SUPPLIERID CHAR(7), 
	STOREID CHAR(7),
	
	CONSTRAINT FK_HASSUPPLIERS_SUPPLIERID FOREIGN KEY (SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID),
	CONSTRAINT FK_HASSUPPLIERS_STOREID FOREIGN KEY (STOREID) REFERENCES STORE(STOREID)
);

INSERT INTO HASSUPPLIERS VALUES ('SUP0001', 'STR0001');
INSERT INTO HASSUPPLIERS VALUES ('SUP0002', 'STR0001');
INSERT INTO HASSUPPLIERS VALUES ('SUP0003', 'STR0002');
INSERT INTO HASSUPPLIERS VALUES ('SUP0004', 'STR0002');
INSERT INTO HASSUPPLIERS VALUES ('SUP0001', 'STR0003');
INSERT INTO HASSUPPLIERS VALUES ('SUP0002', 'STR0003');
INSERT INTO HASSUPPLIERS VALUES ('SUP0004', 'STR0003');
INSERT INTO HASSUPPLIERS VALUES ('SUP0005', 'STR0004');
INSERT INTO HASSUPPLIERS VALUES ('SUP0004', 'STR0004');
INSERT INTO HASSUPPLIERS VALUES ('SUP0006', 'STR0004');
INSERT INTO HASSUPPLIERS VALUES ('SUP0002', 'STR0005');
INSERT INTO HASSUPPLIERS VALUES ('SUP0003', 'STR0005');
INSERT INTO HASSUPPLIERS VALUES ('SUP0004', 'STR0005');
INSERT INTO HASSUPPLIERS VALUES ('SUP0006', 'STR0005');

CREATE TABLE SUPPLIEDPRODUCTS (
	TRANSACTIONID CHAR(10),
	STOREID CHAR(10),
	PRODUCTID CHAR(10),
	BUYQUANTITY INTEGER,
	SUPPLIEDDATE DATE,
	PRODUCTIONDATE DATE,
	EXPIRATIONDATE DATE,
	BATCHID CHAR(10)
);

CREATE TABLE SUPPLY (
	SUPPLIERID CHAR(10),
	TRANSACTIONID CHAR(10)
);

CREATE TABLE PRODUCTINVENTORY (
	PRODUCTID CHAR(10),
	BATCHID CHAR(10),
	QUANTITYINSTOCK INTEGER,
	PRODUCTNAME VARCHAR(64),
	MARKETPRICE DECIMAL(8, 2)
);

CREATE TABLE MAINTAINS (
	ACTIONTYPE VARCHAR(32),
	STAFFID CHAR(10),
	PRODUCTID CHAR(10)
);

CREATE TABLE STOCKS (
	STAFFID CHAR(10),
	PRODUCTID CHAR(10),
	TRANSACTIONID CHAR(10)
);

-- Registration Tables

CREATE TABLE CLUBMEMBERS (
	CUSTOMERID CHAR(10), 
	FIRSTNAME VARCHAR(100), 
	LASTNAME VARCHAR(100), 
	MEMBERSHIPLEVEL VARCHAR(50), 
	EMAILADDRESS VARCHAR(250), 
	PHONENO VARCHAR(15), 
	HOMEADDRESS VARCHAR(1000), 
	ACTIVESTATUS VARCHAR(20)
);

INSERT INTO CLUBMEMBERS VALUES ('CUST000001', 'Ned','Stark', 'Platinum','ned.stark@one.com','911-911-9111','123 Street, Winterfell, Zion','Active');
INSERT INTO CLUBMEMBERS VALUES ('CUST000002', 'Cersei','Lannister', 'Platinum','cersie.lannister@one.com','901-911-9111','11 Street, Westeros Zion','Inactive');
INSERT INTO CLUBMEMBERS VALUES ('CUST000001', 'Jon','Snow', 'Platinum','jon.snow@one.com','9009117111','456 Street, Westeros Zion','Active');
INSERT INTO CLUBMEMBERS VALUES ('CUST000001', 'Sansa','Stark', 'Platinum','sansa.stark@one.com','999-911-9111','123 Street, Winterfell, Zion','Active');
INSERT INTO CLUBMEMBERS VALUES ('CUST000001', 'Ariya','Stark', 'Platinum','ariya.stark@one.com','888-911-9111','123 Street, Winterfell, Zion','Active');
INSERT INTO CLUBMEMBERS VALUES ('CUST000001', 'Jamie','Lannister', 'Platinum','ned.stark@one.com','911-911-9111','11 Street, Westeros Zion','Inactive');


CREATE TABLE SIGNSUPCANCELS (
	STAFFID CHAR(7), 
	CUSTOMERID CHAR(10), 
	ACTION VARCHAR(8),
	ACTIONDATE DATE
);

INSERT INTO SIGNSUPCANCELS VALUES ('STF1111', 'CUST000987', 'signup', '2019-03-15');
INSERT INTO SIGNSUPCANCELS VALUES ('STF1111', 'CUST000988', 'signup', '2019-03-15');
INSERT INTO SIGNSUPCANCELS VALUES ('STF1112', 'CUST000999', 'signup', '2020-03-15');
INSERT INTO SIGNSUPCANCELS VALUES ('STF1113', 'CUST001000', 'signup', '2021-01-17');
INSERT INTO SIGNSUPCANCELS VALUES ('STF1114', 'CUST000987', 'cancel', '2019-03-15');

