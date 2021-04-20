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

            String transactionItemId = null;
            String productID = null;
            String quantitySold = null;
            String totalSoldPrice = null;

            // Add a new transaction - set total price to 0 for now until transaction items are added with the
            // update transaction API
            String sql =  "INSERT INTO TRANSACTIONS VALUES('%s','%s','%s', '%s', 0);";
            String sqlFormatted = null;

            String sqlTransactionItem = "INSERT INTO TRANSACTIONITEMS VALUES ('%s','%s','%s','%d','%f');";
            String sqlTransactionItemFormatted = null;

            int nextTransactionId = 0;
            String sqlTransactionCount = null;
            int rows = 0;

            ResultSet transactionIDresult = null;

            Scanner sc = new Scanner(System.in);
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();

                //Generate next TRANSACTION ID
                try {
                    //sql query for getting all the data from TRANSACTION ID
                    sqlTransactionCount = "SELECT * FROM TRANSACTIONS";
                    try {
                        transactionIDresult = statement.executeQuery(sqlTransactionCount);
                        //going to the last row and checking its row number to generate next  transaction number.
                        transactionIDresult.last();
                        rows = transactionIDresult.getRow();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    nextTransactionId = rows + 1;
                    System.out.println("Transaction ID generated: " + nextTransactionId);


                } catch (Exception e) {
                    System.out.println("Transaction Number could not be generated. Please try again. ");
                } finally {
                    close(transactionIDresult);
                }

                // Gather Transaction details
                try {
                    System.out.print("Enter Customer ID: ");
                    customerID = sc.nextLine();

                    System.out.print("Enter Cashier ID: ");
                    cashierID = sc.nextLine();

                    System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
                    purchaseDate = sc.nextLine();

                    sqlFormatted = String.format(sql, nextTransactionId, customerID, cashierID, purchaseDate);

                } catch (Throwable oops) {
                    System.out.print(oops);
                }
                try{
                    connection.setAutoCommit(false);

                    // Execute insertion into transaction
                    statement.executeQuery(sqlFormatted);

                    connection.commit();

                    System.out.println("Added Transaction");

                    String quit = null;

                    ResultSet transactionItemIDresult = null;

                    do {
                        int nextTransactionItemId = 0;
                        String sqlTransactionItemCount = null;
                        int rowsItem = 0;

                        try {
                            //Generate next TRANSACTION ITEM ID
                            try {
                                //sql query for getting all the data from TRANSACTION ITEM ID
                                sqlTransactionItemCount = "SELECT * FROM TRANSACTIONITEMS";
                                try {
                                    transactionItemIDresult = statement.executeQuery(sqlTransactionItemCount);
                                    //going to the last row and checking its row number to generate next transaction item number.
                                    transactionItemIDresult.last();
                                    rowsItem = transactionIDresult.getRow();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                nextTransactionItemId = rowsItem + 1;
                                System.out.println("Transaction Item ID generated: " + nextTransactionItemId);

                            } catch (Exception e) {
                                System.out.println("Transaction Number could not be generated. Please try again. ");
                            } finally {
                                close(transactionIDresult);
                            }

                            System.out.print("Enter Product ID: ");
                            productID = sc.nextLine();

                            System.out.print("Enter Quantity Sold: ");
                            quantitySold = sc.nextLine();

                            System.out.print("Enter Total Price: ");
                            totalSoldPrice = sc.nextLine();

                            System.out.println("Press any key to enter new item or 'quit' to finish");
                            quit = sc.nextLine();

                            sqlTransactionItemFormatted = String.format(sqlTransactionItem, nextTransactionId, nextTransactionItemId, productID, Integer.parseInt(quantitySold), Double.parseDouble(totalSoldPrice));

                            try {
                                connection.setAutoCommit(false);

                                statement.executeQuery(sqlTransactionItemFormatted);

                                connection.commit();
                            } catch (Exception e) {
                                System.out.println("Failed to Add Transaction Item");
                                connection.rollback();
                                System.out.println(e);
                                return;
                            }


                        } catch (Throwable oops) {
                            System.out.print(oops);
                        }

                    } while (!quit.equals("quit"));



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
                System.out.println(whatever);
            }
        }
    }

    static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Throwable whatever) {
                System.out.println(whatever);
            }
        }
    }

    static void close(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (Throwable whatever) {
                System.out.println(whatever);
            }
        }
    }
}
