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
            String billID = null;
            String staffID = null;

            String CUSTOMERID = null;
            String MEMBERHSIPLEVEL = null;
            double TOTALPURCHASEAMOUNT = null;
            int YEAR = null;

            String sqlMembership = "SELECT A.CUSTOMERID,B.MEMBERSHIPLEVEL, SUM(TOTALPRICE) AS TOTALPURCHASEAMOUNT , " +
                    "YEAR(PURCHASEDATE) AS YEAR FROM TRANSACTIONS A,CLUBMEMBERS B WHERE A.CUSTOMERID = B.CUSTOMERID AND " +
                    "A.CUSTOMERID = '%s' AND YEAR(PURCHASEDATE) = '%s' GROUP BY CUSTOMERID;";
            String sqlRewardsMembershipInsert =  "INSERT INTO CUSTOMERREWARDS VALUES ('%s', %f, '1');";
            String sqlManageCustomerRewardsInsert = "INSERT INTO MANAGESCUSTOMERREWARDS VALUES ('%s','%s','%s');";

            String sqlMembershipFormatted = null;
            String sqlRewardsMembershipInsertFormatted = null;
            String sqlManageCustomerRewardsInsertFormatted = null;

            ResultSet result = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                try {
                    // Get the customer ID and year to see if the customer is platinum for that year
                    System.out.print("Enter Customer ID: ");
                    customerID = sc.nextLine();

                    System.out.print("Enter Year (YYYY): ");
                    year = sc.nextLine();

                    sqlMembershipFormatted = String.format(sql, customerID, year);

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    result = statement.executeQuery(sqlFormatted);

                    // Retrieve the customer's record to check their membership
                    while (result.next()) {
                        CUSTOMERID = result.getString("CUSTOMERID");
                        MEMBERHSIPLEVEL = result.getString("MEMBERHSIPLEVEL");
                        TOTALPURCHASEAMOUNT = result.getString("TOTALPURCHASEAMOUNT");
                        YEAR = result.getString("YEAR");
                    }

                    // Check if the user's membership level is the right level - throw an exception otherwise
                    if(!MEMBERHSIPLEVEL.equals("Platinum")) {
                        throw new Exception("User is not platinum");
                    }

                    // Calculate 2% of the total purchase amount to refund to customer
                    double twoPercentPurchaseAmount = .02 * TOTALPURCHASEAMOUNT;

                    // Now we gather the bill ID and staff ID to enter the rewards check
                    try {
                        System.out.print("Enter Bill ID: ");
                        billID = sc.nextLine();

                        System.out.print("Enter Staff ID: ");
                        staffID = sc.nextLine();

                    } catch (Throwable oops) {
                        System.out.print(oops);
                    }

                    // Create SQL insert statements to update CUSTOMERREWARDS and MANAGESCUSTOMERREWARDS with the
                    // new rewards information
                    sqlRewardsMembershipInsertFormatted = String.format(sqlRewardsMembershipInsert, billID, twoPercentPurchaseAmount);
                    sqlManageCustomerRewardsInsertFormatted = String.format(sqlManageCustomerRewardsInsert, billID, customerID, staffID);

                    statement.executeQuery(sqlRewardsMembershipInsertFormatted);
                    statement.executeQuery(sqlManageCustomerRewardsInsertFormatted);

                    connection.commit();
                    System.out.println("Rewards check generated");

                    return;
                }
                catch (Exception e) {
                    connection.rollback();
                    System.out.println(e);
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
