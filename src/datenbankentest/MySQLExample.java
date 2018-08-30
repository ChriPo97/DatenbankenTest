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
public class MySQLExample {

    public static void main(String[] args) throws Exception {
        MySQLExample mySqlExample = new MySQLExample();
        mySqlExample.readDataBase();
    }

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            //JDBC Treiber und Database URL
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/mysqlexample?serverTimezone=UTC&"
                            + "user=root&password=root");

            //Ausführen einer Datenbankabfrage und Ausgabe in einer Schleife
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from mysqlexample.users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)
                        + " | " + resultSet.getString(3));
            }

            //Zusammensetzung eines SQL Befehls mittels PreparedStatement
            preparedStatement = connect
                    .prepareStatement("insert into  mysqlexample.users values (default, ?, ?)");
            preparedStatement.setString(1, "TestVonJava");
            preparedStatement.setString(2, "TestVonJava");
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
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
