package co.edu.unbosque.taller_4.services;

import co.edu.unbosque.taller_4.dto.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Objects for handling connection
    private Connection conn;

    public UserService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers(String rol) {

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<UserApp> userApps = new ArrayList<UserApp>();

        try {

            // Executing a SQL query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM UserApp WHERE role='"+rol+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while(rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String email  = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                // Creating a new UserApp class instance and adding it to the array list
                userApps.add(new UserApp(username,email, password, role));
            }

            // Printing results
            System.out.println("Username | Email | Password | Role");
            for (UserApp user : userApps) {
                System.out.print(user.getUsername() + " | ");
                System.out.print(user.getEmail() + " | ");
                System.out.print(user.getPassword() + " | ");
                System.out.println(user.getRole());
            }

            // Printing total rows
            System.out.println("Total de users encontrados: " + userApps.size() + "\n");

            // Closing resources
            rs.close();
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
