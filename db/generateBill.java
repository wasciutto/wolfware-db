import java.sql.*;
import java.util.Scanner;

public class generateBill {

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
            String billID = null;
            String suppliedProductTransactionID = null;
            String staffID = null;
            String supplierID = null;
            ResultSet resultID = null;
            String sqlSupplierBill = null;
            String sqlManagesSupplier = null;

            String sqlInsertSupplierBillFormatted = null;
            String sqlManagesSupplierFormatted = null;
            int nextId = 0;
            int rows = 0;
            String sqlcount = null;
            Scanner sc = new Scanner(System.in);
            try {
                // Get a connection instance from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();


                //generating next bill Id number
                try {
                    //sql query for getting all the data from Supplied Products table
                    sqlcount = "SELECT * FROM SUPPLIERBILLS";
                    try {
                        //executing the query
                        resultID = statement.executeQuery(sqlcount);
                        //going to the last row and checking its row number to generate next  transaction number.
                        resultID.last();
                        rows = resultID.getRow();
                    } catch (Exception e) {
                        System.out.println("\n Transaction data could not be loaded. Please try again.");
                    }

                    nextId = rows + 1;


                } catch (Exception e) {
                    System.out.println("Transaction Number could not be generated. Please try again. ");
                }


                try {
                    // Get User Input
                    billID = String.valueOf(nextId);

                    System.out.print("Enter suppliedProductTransactionID: ");
                    suppliedProductTransactionID = sc.nextLine();

                    System.out.print("Enter staffID: ");
                    staffID = sc.nextLine();

                    System.out.print("Enter supplierID: ");
                    supplierID = sc.nextLine();
                    // Insert records in SupplierBills
                    sqlSupplierBill = "INSERT INTO SUPPLIERBILLS(BILLID, BILLAMOUNT, BILLPAID,BATCHID) " +
                            "VALUES(%s,(SELECT (BUYQUANTITY*BUYPRICE)FROM SUPPLIEDPRODUCTS WHERE " +
                            "TRANSACTIONID=%s),FALSE,(SELECT BATCHID FROM SUPPLIEDPRODUCTS " +
                            "WHERE TRANSACTIONID=%s));";

                    sqlManagesSupplier = "INSERT INTO MANAGESSUPPLIERBILLS VALUES (%s, %s, %s);";

                    sqlInsertSupplierBillFormatted = String.format(sqlSupplierBill, "'" + billID + "'", "'" + suppliedProductTransactionID + "'", "'" + suppliedProductTransactionID + "'");
                    sqlManagesSupplierFormatted = String.format(sqlManagesSupplier, "'" + billID + "'", "'" + supplierID + "'", "'" + staffID + "'");

                } catch (Throwable oops) {
                    System.out.print(oops);
                    System.out.print("Incorrect format for billID, suppliedProductTransactionID, staffID, or supplierID");
                }
                try {
                    connection.setAutoCommit(false);

                    statement.executeQuery(sqlInsertSupplierBillFormatted);
                    statement.executeQuery(sqlManagesSupplierFormatted);

                    connection.commit();
                    System.out.println("Bill generated");


                    System.out.println("View updated results? Yes/No?");
                    String decision = sc.nextLine();

                    if (decision.equals("Yes")) {
                        try {
                            //retrieving the result
                            String sqlresult = "SELECT BILLID, BILLAMOUNT,BILLPAID,BATCHID FROM SUPPLIERBILLS";
                            //running the statement to get result.
                            result = statement.executeQuery(sqlresult);

                            //parsing through the ResultSet one by one and displaying all the values.
                            System.out.println("*****************************************************************");

                            System.out.println("| BILLID\t|\tBILLAMOUNT\t|\tBILLPAID\t |");
                            System.out.println("****************************************************************");

                            while (result.next()) {

                                String BILLID = result.getString("BILLID");
                                String BILLAMOUNT = result.getString("BILLAMOUNT");
                                String BILLPAID = result.getString("BILLPAID");
                                //String BATCHID  = result.getString("BATCHID ");
                                System.out.println("| " + BILLID + "\t\t|\t" + BILLAMOUNT + "\t\t|\t" + BILLPAID + "\t\t |");
                            }
                            System.out.println("*****************************************************************");
                        } catch (Exception e) {
                            System.out.println("error in printing the modified changes");
                        }
                    } else {
                        System.out.println("No results!");
                    }


                    return;
                } catch (Exception e) {
                    connection.rollback();
                    System.out.println(e);
                    System.out.println("Bill failed to generate");
                    return;
                }
            } finally {
                close(result);
                close(statement);
                close(connection);
            }
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (Throwable whatever) {
            }
        }
    }


}
