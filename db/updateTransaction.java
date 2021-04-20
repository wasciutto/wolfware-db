import java.sql.*;
import java.util.Scanner;

public class updateTransaction {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

    private static final String user = "pattri";
    private static final String password = "200226336";

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;

            String transactionId = null;

            // Update the total price in TRANSACTIONS to reflect the total sum of totalSoldPrice for the transaction
            String sql =  "UPDATE TRANSACTIONS SET TOTALPRICE = (SELECT SUM(TOTALSOLDPRICE) " +
                    "FROM TRANSACTIONITEMS WHERE TRANSACTIONID = '%s')  WHERE TRANSACTIONID = '%s';";

            String sqlFormatted = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Transaction ID: ");
                    transactionId = sc.nextLine();

                    sqlFormatted = String.format(sql, transactionId, transactionId);

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    statement.executeQuery(sqlFormatted);

                    connection.commit();

                    System.out.println("Updated Transaction");

                    return;
                }
                catch (Exception e) {
                    System.out.println("Failed to Update Transaction");
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
