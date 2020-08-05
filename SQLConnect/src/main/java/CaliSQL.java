import org.apache.log4j.Logger;

import java.sql.*;

public class CaliSQL {

    static Connection caliConn = null;
    static PreparedStatement caliPrepareStat = null;
    static Logger logger = Logger.getLogger(CaliSQL.class);

    public static void main(String[] args) {

        logger.info("-------- Simple Crunchify Tutorial on how to make JDBC connection to MySQL DB locally on macOS ------------");
        makeJDBCConnection();

        logger.info("\n---------- Adding company 'Crunchify LLC' to DB ----------");
        addDataToDB("Crunchify, LLC.", "NYC, US", 5, "https://crunchify.com");
        addDataToDB("Google Inc.", "Mountain View, CA, US", 50000, "https://google.com");
        addDataToDB("Apple Inc.", "Cupertino, CA, US", 30000, "http://apple.com");

        logger.info("\n---------- Let's get Data from DB ----------");
        getDataFromDB();
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
            caliConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crunchify", "root", "root");
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

    private static void addDataToDB(String companyName, String address, int totalEmployee, String webSite) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            caliPrepareStat = caliConn.prepareStatement(insertQueryStatement);
            caliPrepareStat.setString(1, companyName);
            caliPrepareStat.setString(2, address);
            caliPrepareStat.setInt(3, totalEmployee);
            caliPrepareStat.setString(4, webSite);

            // execute insert SQL statement
            caliPrepareStat.executeUpdate();
            log(companyName + " added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDataFromDB() {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM employee";

            caliPrepareStat = caliConn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = caliPrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int employeeCount = rs.getInt("EmployeeCount");
                String website = rs.getString("Website");

                // Simply Print the results
                System.out.format("%s, %s, %s, %s\n", name, address, employeeCount, website);
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }
}
