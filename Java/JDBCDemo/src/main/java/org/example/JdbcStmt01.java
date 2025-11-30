package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStmt01
{
    public static void main(String[] args)
    {

        // Step 1: Load the driver (optional for newer JDBC versions)
        // Class.forName("com.mysql.cj.jdbc.Driver");

        try (
                // Step 2: Create the connection
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/RevatureDB",
                        "root",
                        "MYOWNPASSWORD"
                );

                // Step 3: Create the statement object
                Statement stmt = connection.createStatement();

                // Step 4: Execute query
                ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
        ) {

            System.out.println("Connection Successful");

            // Step 5: Process the result set
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
