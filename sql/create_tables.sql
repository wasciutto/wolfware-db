-- General Tables

CREATE TABLE STORE(
	STOREID CHAR(7), 
	MANAGERID CHAR(7), 
	STOREADDRESS VARCHAR(50), 
	PHONENUMBER VARCHAR(11),
	
	CONSTRAINT PK_STOREID PRIMARY KEY (STOREID)
);

INSERT INTO STORE VALUES ('STR0001','STF0005','Gorman Street,Raleigh','9194321987');
INSERT INTO STORE VALUES ('STR0002','STF0005','Western Blvd.,Raleigh','9192341987');
INSERT INTO STORE VALUES ('STR0003','STF0005','Jackson Street,Raleigh','9194321789');
INSERT INTO STORE VALUES ('STR0004','STF0005','Faucette Dr,Raleigh','9191234987');
INSERT INTO STORE VALUES ('STR0005','STF0005','Clanton Street,Raleigh','9191234567');

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
INSERT INTO SUPPLIERS VALUES ('SUP0007', 'Fresh Farm Eggs','freshfarmeggs@one.com','7778890000','Tootsy NC 27556');
INSERT INTO SUPPLIERS VALUES ('SUP0008', 'First in line stationaries','fil@fils.com','1978898000','Apex NC 27506');
INSERT INTO SUPPLIERS VALUES ('SUP0009', 'Bose','bose@out.com','1965598000','Apex NC 27506');
INSERT INTO SUPPLIERS VALUES ('SUP0010', 'Almonds Valley','a.valley@gout.com','1999598000','Ivory California 34456');

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

CREATE TABLE PRODUCTINVENTORY (
	PRODUCTID CHAR(10),
	BATCHID CHAR(10),
	QUANTITYINSTOCK INTEGER,
	PRODUCTNAME VARCHAR(64),
	MARKETPRICE DECIMAL(8, 2),
	
	CONSTRAINT PK_PRODUCTID PRIMARY KEY (PRODUCTID)
);

INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000001', 'BTC0000001', 56, 'Organic Apples', 7.99);
INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000002', 'BTC0000001', 10, 'Oreo Os', 4.99);
INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000003', 'BTC0000001', 15, '16oz Walnuts', 8.99);
INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000004', 'BTC0000001', 23, 'Activez Protein Bar 8-Pack', 7.99);
INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000005', 'BTC0000001', 19, 'Toliet Paper 12-Pack', 10.99);
INSERT INTO PRODUCTINVENTORY VALUES ('PRD0000006', 'BTC0000001', 78, 'Toothpaste', 1.99);

CREATE TABLE SUPPLIEDPRODUCTS (
	TRANSACTIONID CHAR(12),
	STOREID CHAR(7),
	PRODUCTID CHAR(10),
	BUYQUANTITY INTEGER,
	SUPPLIEDDATE DATE,
	PRODUCTIONDATE DATE,
	EXPIRATIONDATE DATE,
	BATCHID CHAR(10),
	
	CONSTRAINT PK_TRANSACTIONID PRIMARY KEY (TRANSACTIONID),
	CONSTRAINT FK_SUPPLIEDPRODUCTS_STOREID FOREIGN KEY (STOREID) REFERENCES STORE(STOREID),
	CONSTRAINT FK_SUPPLIEDPRODUCTS_PRODUCTID FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID)
);

INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000001', 'STR0001', 'PRD0000001', 100, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000001');
INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000002', 'STR0002', 'PRD0000002', 25, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000002');
INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000003', 'STR0002', 'PRD0000002', 900, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000002');
INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000004', 'STR0003', 'PRD0000003', 45, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000003');
INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000005', 'STR0004', 'PRD0000003', 40, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000004');
INSERT INTO SUPPLIEDPRODUCTS VALUES ('TRN000000006', 'STR0005', 'PRD0000003', 75, '2020-05-05', '2020-03-01', '2020-07-03', 'BTC0000005');

CREATE TABLE SUPPLY (
	SUPPLIERID CHAR(7),
	TRANSACTIONID CHAR(12),
	
	CONSTRAINT FK_SUPPLY_SUPPLIERID FOREIGN KEY (SUPPLIERID) REFERENCES SUPPLIERS(SUPPLIERID),
	CONSTRAINT FK_SUPPLY_TRANSACTIONID FOREIGN KEY (TRANSACTIONID) REFERENCES SUPPLIEDPRODUCTS(TRANSACTIONID)
);

INSERT INTO SUPPLY VALUES ('SUP0001', 'TRN000000001');
INSERT INTO SUPPLY VALUES ('SUP0002', 'TRN000000002');
INSERT INTO SUPPLY VALUES ('SUP0002', 'TRN000000003');
INSERT INTO SUPPLY VALUES ('SUP0003', 'TRN000000004');
INSERT INTO SUPPLY VALUES ('SUP0003', 'TRN000000005');
INSERT INTO SUPPLY VALUES ('SUP0004', 'TRN000000006');

CREATE TABLE MAINTAINS (
	ACTIONTYPE VARCHAR(32),
	STAFFID CHAR(10),
	PRODUCTID CHAR(10),
	
	CONSTRAINT FK_MAINTAINS_STAFFID FOREIGN KEY (STAFFID) REFERENCES STAFF(STAFFID),
	CONSTRAINT FK_MAINTAINS_PRODUCTID FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID)
);

INSERT INTO MAINTAINS VALUES ('Return', 'STF0001', 'PRD0000001');
INSERT INTO MAINTAINS VALUES ('Transfer', 'STF0001', 'PRD0000002');
INSERT INTO MAINTAINS VALUES ('Transfer', 'STF0001', 'PRD0000003');
INSERT INTO MAINTAINS VALUES ('Transfer', 'STF0001', 'PRD0000004');
INSERT INTO MAINTAINS VALUES ('Return', 'STF0001', 'PRD0000005');


CREATE TABLE STOCKS (
	STAFFID CHAR(10),
	PRODUCTID CHAR(10),
	TRANSACTIONID CHAR(10),
	
	CONSTRAINT FK_STOCKS_STAFFID FOREIGN KEY (STAFFID) REFERENCES STAFF(STAFFID),
	CONSTRAINT FK_STOCKS_PRODUCTID FOREIGN KEY (PRODUCTID) REFERENCES PRODUCTINVENTORY(PRODUCTID),
	CONSTRAINT FK_STOCKS_TRANSACTIONID FOREIGN KEY (TRANSACTIONID) REFERENCES SUPPLIEDPRODUCTS(TRANSACTIONID)
);

-- Cashier Tables

CREATE TABLE TRANSACTIONS(
	TRANSACTIONID CHAR(7),
	STOREID CHAR(7),
	CUSTOMERID CHAR(10),
	CASHIERID CHAR(7),
	PURCHASEDATE DATE NOT NULL,
	TOTALPRICE FLOAT NOT NULL,
	PRIMARY KEY (TRANSACTIONID)
);

INSERT INTO TRANSACTIONS VALUES ('TRN0001','STR0001','CUST000001','CSH0001', '2020-05-16', '100');
INSERT INTO TRANSACTIONS VALUES ('TRN0002','STR0002','CUST000002','CSH0002', '2020-05-16', '70');
INSERT INTO TRANSACTIONS VALUES ('TRN0003','STR0003','CUST000001','CSH0003', '2020-05-16', '5');
INSERT INTO TRANSACTIONS VALUES ('TRN0004','STR0004','CUST000003','CSH0001', '2020-05-16', '10');
INSERT INTO TRANSACTIONS VALUES ('TRN0005','STR0005','CUST000003','CSH0002', '2020-05-16', '50');

CREATE TABLE TRANSACTIONITEMS(
	TRANSACTIONID CHAR(7),
	TRANSACTIONITEMID CHAR(10),
	PRODUCTID CHAR(7),
	QUANTITYSOLD INTEGER,
	TOTALSOLDPRICE FLOAT,
	PRIMARY KEY(TRANSACTIONID, TRANSACTIONITEMID),
	FOREIGN KEY(TRANSACTIONID) REFERENCES TRANSACTIONS(TRANSACTIONID) ON UPDATE CASCADE,
);

INSERT INTO TRANSACTIONITEMS VALUES ('TRN0001','TRNITM0001','PRD0001','5','50');
INSERT INTO TRANSACTIONITEMS VALUES ('TRN0002','TRNITM0002','PRD0002','1','75');
INSERT INTO TRANSACTIONITEMS VALUES ('TRN0003','TRNITM0003','PRD0003','2','20');
INSERT INTO TRANSACTIONITEMS VALUES ('TRN0004','TRNITM0004','PRD0004','4','32');
INSERT INTO TRANSACTIONITEMS VALUES ('TRN0005','TRNITM0005','PRD0005','7','55.55');

CREATE TABLE PERFORMSTRANSACTIONS(
	STAFFID CHAR(7),
	TRANSACTIONID CHAR(7),
	PRIMARY KEY(STAFFID,TRANSACTIONID),
	FOREIGN KEY(STAFFID) REFERENCES STAFF(STAFFID) ON UPDATE CASCADE,
	FOREIGN KEY (TRANSACTIONID) REFERENCES TRANSACTIONS(TRANSACTIONID) ON UPDATE CASCADE
);

INSERT INTO PERFORMSTRANSACTIONS VALUES('STF1111','TRN0001');
INSERT INTO PERFORMSTRANSACTIONS VALUES('STF1112','TRN0002');
INSERT INTO PERFORMSTRANSACTIONS VALUES('STF1113','TRN0003');
INSERT INTO PERFORMSTRANSACTIONS VALUES('STF1114','TRN0004');
INSERT INTO PERFORMSTRANSACTIONS VALUES('STF1115','TRN0005');

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