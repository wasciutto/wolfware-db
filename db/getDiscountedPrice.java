import java.sql.*;
import java.util.Scanner;

public class isItemOnSale {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

    private static final String user = "pattri";
    private static final String password = "200226336";

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;

            String productID = null;
            String date = null;
            double DISCOUNTVALUE = null;

            String sql = "SELECT MARKETPRICE-(SELECT A.VALUE FROM DISCOUNT A, HASDISCOUNT B WHERE A.DISCOUNTID = " +
                    "B.DISCOUNTID AND A.STARTDATE < '%s' AND A.ENDDATE > '%s' AND B.PRODUCTID = " +
                    "'%s') FROM PRODUCTINVENTORY WHERE PRODUCTID = '%s';";

            String sqlInRange = "MARKETPRICE-(SELECT A.VALUE FROM DISCOUNT A, HASDISCOUNT B WHERE A.DISCOUNTID = " +
                    "B.DISCOUNTID AND A.STARTDATE < '%s' AND A.ENDDATE > '%s' AND B.PRODUCTID = '%s')";

            String sqlFormatted = null;
            String sqlInRangeFormatted = null;

            ResultSet result = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Product ID: ");
                    customerID = sc.nextLine();

                    System.out.print("Enter Purchase Date to Check (YYYY-MM-DD): ");
                    date = sc.nextLine();

                    sqlFormatted = String.format(sql, date, date, productID);

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    result = statement.executeQuery(sqlFormatted);

                    while (result.next()) {
                        DISCOUNTVALUE = result.getString(1);
                    }

                    connection.commit();

                    System.out.println(DISCOUNTVALUE);
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
