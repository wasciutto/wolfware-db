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
)

CREATE TABLE Supply (
	supplierID CHAR(64),
	transactionID CHAR(64)
)