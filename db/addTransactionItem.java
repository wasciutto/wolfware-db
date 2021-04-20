import java.sql.*;
import java.util.Scanner;

public class addTransaction {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

    private static final String user = "pattri";
    private static final String password = "200226336";

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;

            String transactionId = null;
            String productID = null;
            String quantitySold = null;
            String totalPrice = null;
            String storeID = null;
            String customerID = null;
            String cashierID = null;
            String purchaseDate = null;
            String totalPrice = null;

            String sql2 = "INSERT INTO TRANSACTIONITEMS VALUES ('%s','%s','%s','%s','%s');";
            String sql3 = "UPDATE TRANSACTIONS SET TOTALPRICE = (SELECT SUM(TOTALSOLDPRICE) " +
                    "FROM TRANSACTIONITEMS WHERE TRANSACTIONID = '%s')  WHERE TRANSACTIONID = '%s';";

            String sql1Formatted = null;

            ResultSet result = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Transaction ID: ");
                    transactionId = sc.nextLine();

                    System.out.print("Enter Product ID: ");
                    productID = sc.nextLine();

                    System.out.print("Enter Quantity Sold: ");
                    quantitySold = sc.nextLine();

                    System.out.print("Enter Total Price: ");
                    totalPrice = sc.nextLine();

                    System.out.print("Enter Store ID: ");
                    storeID = sc.nextLine();

                    System.out.print("Enter Customer ID: ");
                    customerID = sc.nextLine();

                    System.out.print("Enter Cashier ID: ");
                    cashierID = sc.nextLine();

                    System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
                    purchaseDate = sc.nextLine();

                    System.out.println("Enter Total Price: ");
                    totalPrice = sc.nextLine();

                    sql1Formatted = String.format(sql, transactionId, customerID, date);

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    result = statement.executeQuery(sqlFormatted);

                    while (result.next()) {
                        DISCOUNTID = result.getString("DISCOUNTID");
                    }

                    connection.commit();
                    if(DISCOUNTID != null) {
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }

                    return;
                }
                catch (Exception e) {
                    connection.rollback();
                    System.out.println(e);
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
