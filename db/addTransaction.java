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
            String customerID = null;
            String cashierID = null;
            String purchaseDate = null;

            String sql =  "INSERT INTO TRANSACTIONS VALUES('%s','%s','%s', '%s', 0);";

            String sqlFormatted = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Transaction ID: ");
                    transactionId = sc.nextLine();

                    System.out.print("Enter Customer ID: ");
                    customerID = sc.nextLine();

                    System.out.print("Enter Cashier ID: ");
                    cashierID = sc.nextLine();

                    System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
                    purchaseDate = sc.nextLine();


                    sqlFormatted = String.format(sql, transactionId, customerID, cashierID, purchaseDate);

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    statement.executeQuery(sqlFormatted);

                    connection.commit();

                    System.out.println("Added Transaction");

                    return;
                }
                catch (Exception e) {
                    System.out.println("Failed to Add Transaction");
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
