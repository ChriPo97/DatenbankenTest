/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbankentest;

import java.sql.*;

/**
 *
 * @author DatenbankenTest
 */
public class MariaDBExample {
    
    public static void main(String[] args) throws Exception {
        MariaDBExample mariaDBExample = new MariaDBExample();
        mariaDBExample.readDataBase();
    }

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MariaDB driver, each DB has its own driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3307/mariadbexample?" + 
                            "user=root&password=root");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from mariadbexample.users");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  mariadbexample.users values (default, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, "TestVonJava");
            preparedStatement.setString(2, "TestVonJava");
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)
                    + " | " + resultSet.getString(3));
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    
}
