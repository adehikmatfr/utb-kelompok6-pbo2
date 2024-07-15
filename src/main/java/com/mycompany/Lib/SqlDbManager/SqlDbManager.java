/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Lib.SqlDbManager;

/**
 *
 * @author macbook
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDbManager implements DbManager {

    private Connection connection;
    private final String url;
    private final String username;
    private final String password;

    public SqlDbManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if (!"".equals(username) && !"".equals(password)) {
                connection = DriverManager.getConnection(url, username, password);
            }else {
                connection = DriverManager.getConnection(url);
            }
        }
    }

    @Override
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Not connected to the database.");
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public int executeUpdate(String query) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Not connected to the database.");
        }
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
}
