import java.sql.*;
import java.util.Scanner;

public class generateRewardsCheck {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/pattri";

    private static final String user = "pattri";
    private static final String password = "200226336";

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = null;
            Statement statement = null;

            String customerID = null;
            String year = null;

            String sqlMembership = null;
            String sqlMembershipFormatted = null;

            ResultSet result = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    System.out.print("Enter Customer ID: ");
                    customerID = sc.nextLine();

                    System.out.print("Enter Year (YYYY): ");
                    year = sc.nextLine();

                    sqlMembership = "SELECT A.CUSTOMERID,B.MEMBERSHIPLEVEL, SUM(TOTALPRICE) AS TOTALPURCHASEAMOUNT , " +
                            "YEAR(PURCHASEDATE) AS YEAR FROM TRANSACTIONS A,CLUBMEMBERS B WHERE A.CUSTOMERID = B.CUSTOMERID AND " +
                            "A.CUSTOMERID = %s AND YEAR(PURCHASEDATE) = %s GROUP BY CUSTOMERID;"

                    sqlMembershipFormatted = String.format(sql, "'" + customerID + "'", year);

                } catch (Throwable oops) {
                    System.out.print(oops)
                    System.out.print("Incorrect format for billID, suppliedProductTransactionID, staffID, or supplierID");
                }
                try{
                    connection.setAutoCommit(false);

                    result = statement.executeQuery(sqlFormatted);

                    connection.commit();
                    System.out.println("Rewards check generated");

                    return;
                }
                catch (Exception e) {
                    connection.rollback();
                    System.out.println(e)
                    System.out.println("Rewards check failed to generate");
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
