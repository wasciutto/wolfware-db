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
            ResultSet result = null;
            String storeID = null;
            int year = 2020;
            String sqlSelect = null;
            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Bill ID: ");
                    String billID = sc.nextLine();

                    System.out.print("Enter suppliedProductTransactionID: ");
                    String suppliedProductTransactionID = sc.nextLine();

                    String sql = "INSERT INTO SUPPLIERBILLS(BILLID, BILLAMOUNT, BILLPAID,BATCHID) " +
                            "VALUES(%s,(SELECT (BUYQUANTITY*BUYPRICE)FROM SUPPLIEDPRODUCTS WHERE " +
                            "TRANSACTIONID=%s),FALSE,(SELECT BATCHID FROM SUPPLIEDPRODUCTS " +
                            "WHERE TRANSACTIONID=%s));"

                    sqlInsert = String.format(sql, "'" + billID + "'", "'" + suppliedProductTransactionID + "'");
                } catch (Throwable oops) {
                    System.out.print("Incorrect format for billID or suppliedProductTransactionID");
                }

                result = statement.executeQuery(sqlInsert);

                if (!statement.execute(sqlSelect)) {
                    System.out.println("Incorrect billID or suppliedProductTransactionID");
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
