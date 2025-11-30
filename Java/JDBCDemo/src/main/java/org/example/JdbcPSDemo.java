package org.example;

import com.mysql.cj.xdevapi.Statement;

import java.sql.*;

public class JdbcPSDemo
{
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RevatureDB", "root", "Bhasker8*");
            String insertQuery = "INSERT INTO employees (id, name, dept) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, "Nikhil");
            pstmt.setString(2, "thedemo2146@gmail.com");
            pstmt.setString(3, "123456768");
            pstmt.execute();
            pstmt.close();

            String selectQuery ="select * from contacts where name like ?";
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, "Nikhil");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
