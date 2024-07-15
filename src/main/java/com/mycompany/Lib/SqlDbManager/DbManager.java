/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Lib.SqlDbManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author macbook
 */
public interface DbManager {
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    ResultSet executeQuery(String query) throws SQLException;
    int executeUpdate(String query) throws SQLException;
}
