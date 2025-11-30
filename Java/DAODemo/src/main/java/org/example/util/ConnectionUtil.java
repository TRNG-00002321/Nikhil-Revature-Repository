package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil
{
    static Connection conn = null;
    public static Connection dbConnetion()
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RevatureDB", "root", "Bhasker8*");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return conn;
    }
}
