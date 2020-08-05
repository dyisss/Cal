import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class CaliSQL {

    private static Connection caliConn = null;
    private static PreparedStatement caliPrepareStat = null;
    private static Logger logger = Logger.getLogger(CaliSQL.class);

    public static void connect() {

        logger.info("-------- Simple  JDBC connection to MySQL DB locally ------------");
        makeJDBCConnection();

        logger.info("\n---------- Let's get Data from DB ----------");
    }

    private static void makeJDBCConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            logger.info("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            caliConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "dyis", "Egnamatic1");
            if (caliConn != null) {
                logger.info("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                logger.info("Failed to make connection!");
            }
        } catch (SQLException e) {
            logger.info("MySQL Connection Failed!");
            e.printStackTrace();
        }
    }

    public static void addDataToDBStadium(int numSeats, String name) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            caliPrepareStat = caliConn.prepareStatement(insertQueryStatement);
            caliPrepareStat.setInt(1, numSeats);
            caliPrepareStat.setString(2, name);

            // execute insert SQL statement
            caliPrepareStat.executeUpdate();
            log(name + " added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataToUser(String name, int matchID, int ticketID) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            caliPrepareStat = caliConn.prepareStatement(insertQueryStatement);
            caliPrepareStat.setString(1, name);
            caliPrepareStat.setInt(2, ticketID);

            // execute insert SQL statement
            caliPrepareStat.executeUpdate();
            log(name + " added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataToDBMatch( Date eventDate, String eventType) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            caliPrepareStat = caliConn.prepareStatement(insertQueryStatement);
            caliPrepareStat.setDate(1, eventDate);
            caliPrepareStat.setString(2, eventType);

            // execute insert SQL statement
            caliPrepareStat.executeUpdate();
            log( "match added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataToDBTicket(String seatType, int seatNumber, int matchID) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            caliPrepareStat = caliConn.prepareStatement(insertQueryStatement);
            caliPrepareStat.setString(1, seatType);
            caliPrepareStat.setInt(2,seatNumber);
            caliPrepareStat.setInt(3,matchID);

            // execute insert SQL statement
            caliPrepareStat.executeUpdate();
            log( "ticket added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromDB(String tableName, ArrayList returnedList) {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM "+ tableName;

            caliPrepareStat = caliConn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = caliPrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            if ("user".equals(tableName)) {
                while (rs.next()) {
                    // TODO add user object here
                    // Example here String name = rs.getString("Name");
                    //				String address = rs.getString("Address");
                    //				int employeeCount = rs.getInt("EmployeeCount");
                    //				String website = rs.getString("Website");
                    // Simply Print the results
                    System.out.println("%s, %s, %s, %s\n");
                }
            } else if ("stadium".equals(tableName)) {
                while (rs.next()) {
                    // TODO add stadium object here
                    // Simply Print the results
                    System.out.println("%s, %s, %s, %s\n");
                }
            } else if ("ticket".equals(tableName)) {
                while (rs.next()) {
                    // TODO add ticket object here
                    // Simply Print the results
                    System.out.println("%s, %s, %s, %s\n");
                }
            } else if ("match".equals(tableName)) {
                while (rs.next()) {
                    // TODO add match object here
                    // Simply Print the results
                    System.out.println("%s, %s, %s, %s\n");
                }
            } else {
                throw new IllegalStateException("Unexpected value: " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }
}
