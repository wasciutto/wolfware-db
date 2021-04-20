import java.sql.*;
import java.util.Scanner;

public class addTransactionItem {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

    private static final String user = "pattri";
    private static final String password = "200226336";

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;

            String transactionId = null;
            String transactionItemId = null;
            String productID = null;
            String quantitySold = null;
            String totalSoldPrice = null;

            // Insert a new transaction item into the database. Its totalSoldPrice is used in TRANSACTIONS to calculate
            // the total value of a TRANSACTION with many TRANSACTIONITEMs.
            String sql = "INSERT INTO TRANSACTIONITEMS VALUES ('%s','%s','%s','%d','%f');";

            String sqlFormatted = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Transaction ID: ");
                    transactionId = sc.nextLine();

                    System.out.print("Enter Transaction Item ID: ");
                    transactionItemId = sc.nextLine();

                    System.out.print("Enter Product ID: ");
                    productID = sc.nextLine();

                    System.out.print("Enter Quantity Sold: ");
                    quantitySold = sc.nextLine();

                    System.out.print("Enter Total Price: ");
                    totalSoldPrice = sc.nextLine();

                    sqlFormatted = String.format(sql, transactionId, transactionItemId, productID, Integer.parseInt(quantitySold), Double.parseDouble(totalSoldPrice));

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    statement.executeQuery(sqlFormatted);

                    connection.commit();

                    System.out.println("Added Transaction Item");

                    return;
                }
                catch (Exception e) {
                    System.out.println("Failed to Add Transaction Item");
                    connection.rollback();
                    System.out.println(e);
                    return;
                }
            } finally {
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
