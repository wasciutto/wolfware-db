import java.sql.*;
import java.util.Scanner;

public class generateBill {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

    private static final String user = "pattri";
    private static final String password = "200226336";

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;

            String billID = null;
            String suppliedProductTransactionID = null;
            String staffID = null;
            String supplierID = null;

            String sqlSupplierBill = null;
            String sqlManagesSupplier = null;

            String sqlInsertSupplierBillFormatted = null;
            String sqlManagesSupplierFormatted = null;

            ResultSet resultSupplierBill = null;
            ResultSet resultManagesSupplier = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter billID: ");
                    billID = sc.nextLine();

                    System.out.print("Enter suppliedProductTransactionID: ");
                    suppliedProductTransactionID = sc.nextLine();

                    System.out.print("Enter staffID: ");
                    staffID = sc.nextLine();

                    System.out.print("Enter supplierID: ");
                    supplierID = sc.nextLine();

                    sqlSupplierBill = "INSERT INTO SUPPLIERBILLS(BILLID, BILLAMOUNT, BILLPAID,BATCHID) " +
                            "VALUES(%s,(SELECT (BUYQUANTITY*BUYPRICE)FROM SUPPLIEDPRODUCTS WHERE " +
                            "TRANSACTIONID=%s),FALSE,(SELECT BATCHID FROM SUPPLIEDPRODUCTS " +
                            "WHERE TRANSACTIONID=%s));";

                    sqlManagesSupplier = "INSERT INTO MANAGESSUPPLIERBILLS VALUES (%s, %s, %s);";

                    sqlInsertSupplierBillFormatted = String.format(sqlSupplierBill, "'" + billID + "'", "'" + suppliedProductTransactionID + "'");
                    sqlManagesSupplierFormatted = String.format(sqlManagesSupplier, "'" + billID + "'", "'" + supplierID + "'", "'" + staffID + "'");

                } catch (Throwable oops) {
                    System.out.print(oops);
                    System.out.print("Incorrect format for billID, suppliedProductTransactionID, staffID, or supplierID");
                }
                try{
                    connection.setAutoCommit(false);

                    resultSupplierBill = statement.executeQuery(sqlInsertSupplierBillFormatted);
                    resultManagesSupplier = statement.executeQuery(sqlManagesSupplierFormatted);

                    connection.commit();
                    System.out.println("Bill generated");

                    return;
                }
                catch (Exception e) {
                    connection.rollback();
                    System.out.println(e);
                    System.out.println("Bill failed to generate");
                    return;
                }
            } finally {
                close(resultSupplierBill);
                close(resultManagesSupplier);
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
