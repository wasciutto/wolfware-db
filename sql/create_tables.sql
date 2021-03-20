CREATE TABLE Staff (
    staffID CHAR(64),
    storeID CHAR(64),
    name VARCHAR(128),
    age INTEGER,
    homeAddress VARCHAR(128),
    jobTitle VARCHAR(64),
    phoneNumber VARCHAR(16),
    emailAddress VARCHAR(64),
    timeOfEmployment TIMESTAMP
);

CREATE TABLE Supply (
	supplierID CHAR(64),
	transactionID CHAR(64)
);

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

CREATE TABLE SUPPLIERS (
	SUPPLIERID CHAR(7), 
	SUPPLIERNAME CHAR(150), 
	EMAILADDRESS VARCHAR(250), 
	PHONE VARCHAR(15), 
	LOCATION  VARCHAR(1000)
);

INSERT INTO SUPPLIERS VALUES ('SUP0001','Fresh Farm Eggs','freshfarmeggs@one.com','7778890000','Tootsy NC 27556');
INSERT INTO SUPPLIERS VALUES ('SUP0002','First in line stationaries','fil@fils.com','1978898000','Apex NC 27506');
INSERT INTO SUPPLIERS VALUES ('SUP0003','Bose','bose@out.com','1965598000','Apex NC 27506');
INSERT INTO SUPPLIERS VALUES ('SUP0004','Almonds Valley','a.valley@gout.com','1999598000','Ivory California 34456');

CREATE TABLE HASSUPPLIERS (
	SUPPLIERID CHAR(7), 
	STOREID CHAR(7)
);

INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0001');
INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0002');
INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0003');
INSERT INTO HASSUPPLIERS VALUES ('STR0010','SUP0004');
INSERT INTO HASSUPPLIERS VALUES ('STR0020','SUP0002');
INSERT INTO HASSUPPLIERS VALUES ('STR0020','SUP0003');
INSERT INTO HASSUPPLIERS VALUES ('STR0030','SUP0002');
INSERT INTO HASSUPPLIERS VALUES ('STR0030','SUP0004');

CREATE TABLE STORE(
	STOREID CHAR(7), 
	MANAGERID CHAR(7), 
	STOREADDRESS VARCHAR(50), 
	PHONENUMBER VARCHAR(11)
);

