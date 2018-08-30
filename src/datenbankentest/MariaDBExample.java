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
            Class.forName("org.mariadb.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3307/mariadbexample?" + 
                            "user=root&password=root");

            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from mariadbexample.users");
            writeResultSet(resultSet);

            preparedStatement = connect
                    .prepareStatement("insert into  mariadbexample.users values (default, ?, ?)");
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
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)
                    + " | " + resultSet.getString(3));
        }
    }

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
